package web2final

enum TipoUsuario {
    GENERICO("Normal"),
    CLIENTE_CONSUMIDOR_FINAL("Consumidor Final"),
    CLIENTE_EMPRESA("Cliente Empresarial"),
    CLIENTE_PERSONA_FISICA("Persona Fisica"),
    VENTAS("Empleado de Ventas"),
    ALMACEN("Empleado de Almacen"),
    ADMIN("Administrador")

    final String val

    TipoUsuario(String value) {
        this.val = value;
    }

    String toString() {
        return this.val
    }

    String getKey() {
        return this.name()
    }
}