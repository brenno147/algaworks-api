package com.tce.estagio.domain.entity;

import com.tce.estagio.domain.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_id_seq",
            sequenceName = "student_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_id_seq",
            strategy = javax.persistence.GenerationType.SEQUENCE
    )
    private Long studentId;

    @NotBlank
    private String firsName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @Embedded
    private Guardian guardian;
}
