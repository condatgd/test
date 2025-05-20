package de.berlin.gd.kantine.controller.ereignis.model;

import lombok.Data;

@Data
public class EreignisDTO {
    String user;
    EreignisTypDTO ereignisTyp;
}
