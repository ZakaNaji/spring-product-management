package com.znaji.productmanagement.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    private Integer id;
    @Column(name = "product_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "category_id", insertable = false, updatable = false)
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @Column(name = "supplier_id", insertable = false, updatable = false)
    private Integer supplierId;
    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;
    @Column(name = "unit_price")
    private Double unitPrice;
    @Column(name = "units_in_stock")
    private Integer unitsInStock;
    @Column(name = "units_on_order")
    private Integer unitsOnOrder;
    @Column(name = "reorder_level")
    private Integer reorderLevel;
    @Column(name = "discontinued")
    private Integer discontinued;
}
