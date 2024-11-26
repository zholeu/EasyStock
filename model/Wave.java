package com.springeasystock.easystock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "wave")
public class Wave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @OneToMany(fetch = FetchType.LAZY)
//    @OneToMany(cascade = CascadeType.ALL)
    @OneToMany
    @JoinColumn(name = "fk_order_list_id", referencedColumnName = "id")
//    private OrderList orderList;
    private Set<OrderList> orderList;

    @Column(name = "wave_priority")
    private String wavePriority;

//    @Column(name = "wave_status", nullable = false)
    @Column(name = "wave_status")
    private String waveStatus;

//    @OneToMany(mappedBy = "wave", cascade = CascadeType.ALL)
//    private Set<Zone> zones;
}

