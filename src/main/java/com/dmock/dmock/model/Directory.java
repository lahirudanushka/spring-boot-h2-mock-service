package com.dmock.dmock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "directory")
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String packageName;
    @Lob
    private String body;
    @Column(columnDefinition = "int4 default 1")
    private Integer status;
    private Integer responseCode;
    @ElementCollection
    private List<String> supportMethods;
    private String mediaType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer count;

}
