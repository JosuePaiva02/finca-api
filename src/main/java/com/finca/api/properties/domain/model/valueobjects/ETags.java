package com.finca.api.properties.domain.model.valueobjects;

public enum ETags {
    // MAS AMBIENTES
    COMEDOR_DIARIO(ETagCategory.MAS_AMBIENTES),
    DORMITORIO_PRINCIPAL(ETagCategory.MAS_AMBIENTES),
    INGRESO_INDEPENDIENTE(ETagCategory.MAS_AMBIENTES),
    PATIO(ETagCategory.MAS_AMBIENTES),
    SALA_DE_ESTAR(ETagCategory.MAS_AMBIENTES),
    AREA_DE_BBQ(ETagCategory.MAS_AMBIENTES),

    // SERVICIOS
    AREA_DE_LAVANDERIA(ETagCategory.SERVICIOS),
    AREAS_VERDES(ETagCategory.SERVICIOS),
    GUARDIANIA(ETagCategory.SERVICIOS),
    PARRILLA(ETagCategory.SERVICIOS),
    SERVICIOS_BASICOS(ETagCategory.SERVICIOS),
    TELEVISION_POR_CABLE(ETagCategory.SERVICIOS),
    GIMNASIO(ETagCategory.SERVICIOS),
    INTERNET(ETagCategory.SERVICIOS),
    LINEA_BLANCA(ETagCategory.SERVICIOS),
    ILUMINARIAS(ETagCategory.SERVICIOS),
    SALA_Y_COMEDOR(ETagCategory.SERVICIOS),
    JUEGOS_INFANTILES(ETagCategory.SERVICIOS),
    SERVICIO_DE_LIMPIEZA(ETagCategory.SERVICIOS),
    SISTEMA_DE_ALARMA(ETagCategory.SERVICIOS),
    USO_COMERCIAL(ETagCategory.SERVICIOS),
    USO_PROFESIONAL(ETagCategory.SERVICIOS),
    VIDEO_VIGILANCIA(ETagCategory.SERVICIOS),

    // EXTRAS
    COCINA(ETagCategory.EXTRAS),
    CUARTO_DE_SERVICIO(ETagCategory.EXTRAS),
    ESCUELAS_CERCANAS(ETagCategory.EXTRAS),
    JARDIN(ETagCategory.EXTRAS),
    PET_FRIENDLY(ETagCategory.EXTRAS),
    TERRAZA(ETagCategory.EXTRAS),
    ACABADOS_DE_LUJO(ETagCategory.EXTRAS),
    AMOBLADO(ETagCategory.EXTRAS),
    CASETA_DE_GUARDIA(ETagCategory.EXTRAS),
    CENTROS_COMERCIALES_CERCANOS(ETagCategory.EXTRAS),
    CHIMENEA(ETagCategory.EXTRAS),
    CLOSET(ETagCategory.EXTRAS),
    COCINA_INTEGRAL(ETagCategory.EXTRAS),
    EN_CONDOMINIO(ETagCategory.EXTRAS),
    FRENTE_AL_MAR(ETagCategory.EXTRAS),
    FRENTE_AL_PARQUE(ETagCategory.EXTRAS),
    INTERCOMUNICADOR(ETagCategory.EXTRAS),
    JACUZZI(ETagCategory.EXTRAS),
    PARQUES_CERCANOS(ETagCategory.EXTRAS),
    PISCINA(ETagCategory.EXTRAS),
    SEGURIDAD(ETagCategory.EXTRAS),
    VISTA_A_LA_CIUDAD(ETagCategory.EXTRAS),
    VISTA_AL_MAR(ETagCategory.EXTRAS),
    VISTA_AL_PARQUE(ETagCategory.EXTRAS),
    WALK_IN_CLOSET(ETagCategory.EXTRAS),
    ZONA_INDUSTRIAL(ETagCategory.EXTRAS);

    private final ETagCategory category;

    ETags(ETagCategory category) {
        this.category = category;
    }

    public ETagCategory getCategory() {
        return category;
    }
}
