package com.finca.api.properties.domain.model.valueobjects;

public enum ETags {

    // MAS AMBIENTES
    COMEDOR_DIARIO("Comedor diario", ETagCategory.MAS_AMBIENTES),
    DORMITORIO_PRINCIPAL("Dormitorio principal", ETagCategory.MAS_AMBIENTES),
    INGRESO_INDEPENDIENTE("Ingreso independiente", ETagCategory.MAS_AMBIENTES),
    PATIO("Patio", ETagCategory.MAS_AMBIENTES),
    SALA_DE_ESTAR("Sala de estar", ETagCategory.MAS_AMBIENTES),
    AREA_DE_BBQ("Área de BBQ", ETagCategory.MAS_AMBIENTES),

    // SERVICIOS
    AREA_DE_LAVANDERIA("Área de lavandería", ETagCategory.SERVICIOS),
    AREAS_VERDES("Áreas verdes", ETagCategory.SERVICIOS),
    GUARDIANIA("Guardianía", ETagCategory.SERVICIOS),
    PARRILLA("Parrilla", ETagCategory.SERVICIOS),
    SERVICIOS_BASICOS("Servicios básicos", ETagCategory.SERVICIOS),
    TELEVISION_POR_CABLE("Televisión por cable", ETagCategory.SERVICIOS),
    GIMNASIO("Gimnasio", ETagCategory.SERVICIOS),
    INTERNET("Internet", ETagCategory.SERVICIOS),
    LINEA_BLANCA("Línea blanca", ETagCategory.SERVICIOS),
    ILUMINARIAS("Iluminarias", ETagCategory.SERVICIOS),
    SALA_Y_COMEDOR("Sala y comedor", ETagCategory.SERVICIOS),
    JUEGOS_INFANTILES("Juegos infantiles", ETagCategory.SERVICIOS),
    SERVICIO_DE_LIMPIEZA("Servicio de limpieza", ETagCategory.SERVICIOS),
    SISTEMA_DE_ALARMA("Sistema de alarma", ETagCategory.SERVICIOS),
    USO_COMERCIAL("Uso comercial", ETagCategory.SERVICIOS),
    USO_PROFESIONAL("Uso profesional", ETagCategory.SERVICIOS),
    VIDEO_VIGILANCIA("Video vigilancia", ETagCategory.SERVICIOS),

    // EXTRAS
    COCINA("Cocina", ETagCategory.EXTRAS),
    CUARTO_DE_SERVICIO("Cuarto de servicio", ETagCategory.EXTRAS),
    ESCUELAS_CERCANAS("Escuelas cercanas", ETagCategory.EXTRAS),
    JARDIN("Jardín", ETagCategory.EXTRAS),
    PET_FRIENDLY("Pet friendly", ETagCategory.EXTRAS),
    TERRAZA("Terraza", ETagCategory.EXTRAS),
    ACABADOS_DE_LUJO("Acabados de lujo", ETagCategory.EXTRAS),
    AMOBLADO("Amoblado", ETagCategory.EXTRAS),
    CASETA_DE_GUARDIA("Caseta de guardia", ETagCategory.EXTRAS),
    CENTROS_COMERCIALES_CERCANOS("Centros comerciales cercanos", ETagCategory.EXTRAS),
    CHIMENEA("Chimenea", ETagCategory.EXTRAS),
    CLOSET("Clóset", ETagCategory.EXTRAS),
    COCINA_INTEGRAL("Cocina integral", ETagCategory.EXTRAS),
    EN_CONDOMINIO("En condominio", ETagCategory.EXTRAS),
    FRENTE_AL_MAR("Frente al mar", ETagCategory.EXTRAS),
    FRENTE_AL_PARQUE("Frente al parque", ETagCategory.EXTRAS),
    INTERCOMUNICADOR("Intercomunicador", ETagCategory.EXTRAS),
    JACUZZI("Jacuzzi", ETagCategory.EXTRAS),
    PARQUES_CERCANOS("Parques cercanos", ETagCategory.EXTRAS),
    PISCINA("Piscina", ETagCategory.EXTRAS),
    SEGURIDAD("Seguridad", ETagCategory.EXTRAS),
    VISTA_A_LA_CIUDAD("Vista a la ciudad", ETagCategory.EXTRAS),
    VISTA_AL_MAR("Vista al mar", ETagCategory.EXTRAS),
    VISTA_AL_PARQUE("Vista al parque", ETagCategory.EXTRAS),
    WALK_IN_CLOSET("Walk-in closet", ETagCategory.EXTRAS),
    ZONA_INDUSTRIAL("Zona industrial", ETagCategory.EXTRAS);

    private final String displayName;
    private final ETagCategory category;

    ETags(String displayName, ETagCategory category) {
        this.displayName = displayName;
        this.category = category;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ETagCategory getCategory() {
        return category;
    }
}
