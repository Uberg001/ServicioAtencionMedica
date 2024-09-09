# Servicio Atención Médica
La ejecución del presente servicio retorna una lista de las localidades con la cantidad de personas registradas en cada una, asi como el nombre y la localización de cada una.
* Entidades
  * Coordenada
     ```
     BigDecimal latitud;
     BigDecimal longitud;
     ```
  * Direccion
    ```
    String calle;
    String calle;
    Localidad localidad;
    Localidad localidad;
    ```
  * Documento
    ```
    TipoDocumento tipo;
    String numero;
    ```
  * Localidad
    ```
    String nombre;
    Partido partido;
    ```
  * Partido
    ```
    String nombre;
    Provincia provincia;
    ```
  * PersonaVulnerable
    ```
    String firstName;
    String lastName;
    String email;
    Documento documento;
    Direccion domicilio;
    ```
  * Provincia
    ```
    String nombre;
    ```
  * TipoDocumento
    ```
    <enum>
    DNI
    LC
    LE
    ```

# Endpoints
* /api/ServicioAtencionMedica/{id} [DELETE]
  * Estados:
    * 200: OK
    * 404: NOT FOUND
    * 500: INTERNAL SERVER ERROR
* /api/ServicioAtencionMedica/{id} [GET] (Obtiene una persona. Se usa el id=2 para ejemplificar)
  * Estados:
    * 200: OK
    * 404: NOT FOUND
    * 500: INTERNAL SERVER ERROR
  ```
  {
    "id": 2,
    "firstName": "Juan",
    "lastName": "Perez",
    "email": "juan.perez@example.com",
    "documento": {
        "id": 6,
        "tipo": "DNI",
        "numero": "12345678"
    },
    "domicilio": {
        "id": 6,
        "calle": "Calle Falsa",
        "altura": 123,
        "localidad": {
            "id": 6,
            "nombre": "Localidad Ejemplo",
            "partido": {
                "id": 6,
                "nombre": "Partido Ejemplo",
                "provincia": {
                    "id": 6,
                    "nombre": "Provincia Ejemplo"
                }
            }
        },
        "coordenada": {
            "id": 6,
            "latitud": 12.345678,
            "longitud": 98.765432
        }
    }
  }
  ```
* /api/ServicioAtencionMedica/{id} [PUT] (Actualizar persona. Se ingresa id=2 para ejemplificar)
  * Estados:
    * 200: OK
    * 404: NOT FOUND
    * 500: INTERNAL SERVER ERROR
  ```
  {
    "id": 2,
    "firstName": "Juan",
    "lastName": "Perez",
    "email": "juan.perez@example.com",
    "documento": {
        "id": 6,
        "tipo": "DNI",
        "numero": "12345678"
    },
    "domicilio": {
        "id": 6,
        "calle": "Calle Falsa",
        "altura": 123,
        "localidad": {
            "id": 6,
            "nombre": "Localidad Ejemplo",
            "partido": {
                "id": 6,
                "nombre": "Partido Ejemplo",
                "provincia": {
                    "id": 6,
                    "nombre": "Provincia Ejemplo"
                }
            }
        },
        "coordenada": {
            "id": 6,
            "latitud": 12.345678,
            "longitud": 98.765432
        }
    }
  }
  ```
* /api/ServicioAtencionMedica/create [POST]
  * Estados:
    * 200: OK
    * 404: NOT FOUND
    * 500: INTERNAL SERVER ERROR
* /api/ServicioAtencionMedica/deleteAll [DELETE]
  * Estados:
    * 200: OK
    * 404: NOT FOUND
    * 500: INTERNAL SERVER ERROR
* /api/ServicioAtencionMedica/reporteLocalidades [GET] (lista de todas las personas dadas de alta)
  * Estados:
    * 200: OK
    * 404: NOT FOUND
    * 500: INTERNAL SERVER ERROR
  ```
  [
    {
        "id": 2,
        "firstName": "Juan",
        "lastName": "Perez",
        "email": "juan.perez@example.com",
        "documento": {
            "id": 2,
            "tipo": "DNI",
            "numero": "12345678"
        },
        "domicilio": {
            "id": 2,
            "calle": "Calle Falsa",
            "altura": 123,
            "localidad": {
                "id": 2,
                "nombre": "Localidad Ejemplo",
                "partido": {
                    "id": 2,
                    "nombre": "Partido Ejemplo",
                    "provincia": {
                        "id": 2,
                        "nombre": "Provincia Ejemplo"
                    }
                }
            },
            "coordenada": {
                "id": 2,
                "latitud": 12.35,
                "longitud": 98.77
            }
        }
    },
    {
        "id": 3,
        "firstName": "Juan",
        "lastName": "Perez",
        "email": "juan.perez@example.com",
        "documento": {
            "id": 3,
            "tipo": "DNI",
            "numero": "12345678"
        },
        "domicilio": {
            "id": 3,
            "calle": "Calle Falsa",
            "altura": 123,
            "localidad": {
                "id": 3,
                "nombre": "Localidad Ejemplo",
                "partido": {
                    "id": 3,
                    "nombre": "Partido Ejemplo",
                    "provincia": {
                        "id": 3,
                        "nombre": "Provincia Ejemplo"
                    }
                }
            },
            "coordenada": {
                "id": 3,
                "latitud": 12.35,
                "longitud": 98.77
            }
        }
    },
    {
        "id": 4,
        "firstName": "Juan",
        "lastName": "Perez",
        "email": "juan.perez@example.com",
        "documento": {
            "id": 4,
            "tipo": "DNI",
            "numero": "12345678"
        },
        "domicilio": {
            "id": 4,
            "calle": "Calle Falsa",
            "altura": 123,
            "localidad": {
                "id": 4,
                "nombre": "Localidad Ejemplo",
                "partido": {
                    "id": 4,
                    "nombre": "Partido Ejemplo",
                    "provincia": {
                        "id": 4,
                        "nombre": "Provincia Ejemplo"
                    }
                }
            },
            "coordenada": {
                "id": 4,
                "latitud": 12.35,
                "longitud": 98.77
            }
        }
    },
    {
        "id": 5,
        "firstName": "Juan",
        "lastName": "Perez",
        "email": "juan.perez@example.com",
        "documento": {
            "id": 5,
            "tipo": "DNI",
            "numero": "12345678"
        },
        "domicilio": {
            "id": 5,
            "calle": "Calle Falsa",
            "altura": 123,
            "localidad": {
                "id": 5,
                "nombre": "Localidad Ejemplo",
                "partido": {
                    "id": 5,
                    "nombre": "Partido Ejemplo",
                    "provincia": {
                        "id": 5,
                        "nombre": "Provincia Ejemplo"
                    }
                }
            },
            "coordenada": {
                "id": 5,
                "latitud": 12.35,
                "longitud": 98.77
            }
        }
    }
  ]
  ```
