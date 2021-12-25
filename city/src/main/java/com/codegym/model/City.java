package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name not empty")
    @Pattern(regexp = "(^([AÀẢÃÁẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÈÉẺẼẸÊỀẾỂỄỆFGHIÍÌỈĨỊJKLMNOÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢPQRSTUÙÚỦŨỤƯỪỨỬỮỰVWXYÝỲỶỸỴZ]+[aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*?[ ]?)+$)", message = "Name format not true, Ex example : Nguyễn Văn A ")
    @Size(min=1 , max=45 ,message = "Full name description within 255 characters ! ")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @Digits(integer = 12, fraction = 0)
    private BigDecimal acreage ;

    @Digits(integer = 12, fraction = 0)
    private BigDecimal population;

    @Min(value = 0,message = "GDP khong phai so am")
    private int gdp ;

    private String description ;

    private boolean isDelete = false ;
}
