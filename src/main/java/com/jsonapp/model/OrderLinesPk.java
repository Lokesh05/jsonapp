package com.jsonapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderLinesPk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "line_number", nullable = false)
    private String lineNumber;
}
