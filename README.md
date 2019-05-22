# CalculatorApi

Calculadora para prueba Cyxtera

## Instalación

* Clonar el proyecto e importar en el IDE de Spring Tools.
* Sobre el proyecto ejecutar Maven -> Update Project..
* Sobre el proyecto ejecutar Run As -> Spring Boot App.

# Operaciones Basicas
# GetSessionID

    GET /calculatorApi/getSessionId
    
Genera y retorna el ID de sesion.

## Parameters
N/A

## Example
### Request

    http://localhost:8080/calculatorApi/getSessionId

### Response
``` json
{
    "responseCode": 200,
    "message": "Id creado correctamente.",
    "sessionId": 4
}
```

# AddOperand

    POST /calculatorApi/addOperand/{value}/{idSession}
    
Inserta el valor del operando en la base de datos con su respectivo id.

## Parameters
N/A
### URI Parameters
Field | Wildcards | Description
--- | --- | ---
value | N | Valor del operando (Double)
idSession | N | Id de la Sesión (long)

## Example
### Request

    http://localhost:8080/calculatorApi/addOperand/3.5/1

### Response
``` json
{
    "responseCode": 0,
    "message": "Operando creado correctamente.",
    "operand": 3.5
}
```
# SetOperator

    POST /calculatorApi/setOperator/{operator}/{idSession}
    
Inserta el operador y calcula el resultado.

## Parameters
N/A
### URI Parameters
Field | Wildcards | Description
--- | --- | ---
operator | N | Tipo de operacion (suma, resta, multiplicacion, division y potenciacion) (String)
idSession | N | Id de la Sesión (long)

## Example
### Request

    http://localhost:8080/calculatorApi/setOperator/suma/1

### Response
``` json
{
    "responseCode": 0,
    "message": "Operación ejecutada correctamente.",
    "operator": "suma",
    "result": 3.5
}
```
# Consultas
# Sesiones

    GET /consultar/sesiones
    
Retorna las Sesiones.

## Parameters
N/A

## Example
### Request

    http://localhost:8080/consultar/sesiones

### Response
``` json
[
    {
        "id": 1,
        "status": "Activo",
        "operands": null,
        "operators": null,
        "createAt": "2019-05-21"
    },
    {
        "id": 2,
        "status": "Activo",
        "operands": null,
        "operators": null,
        "createAt": "2019-05-21"
    },
    {
        "id": 3,
        "status": "Activo",
        "operands": null,
        "operators": null,
        "createAt": "2019-05-21"
    },
    {
        "id": 4,
        "status": "Activo",
        "operands": null,
        "operators": null,
        "createAt": "2019-05-21"
    },
    {
        "id": 5,
        "status": "Activo",
        "operands": null,
        "operators": null,
        "createAt": "2019-05-21"
    }
]
```

# Operandos

    GET /consultar/operandos
    
Retorna los operandos.

## Parameters
N/A

## Example
### Request

    http://localhost:8080/consultar/operandos

### Response
``` json
[
    {
        "id": 1,
        "value": 3.5,
        "status": "Activo",
        "sessionId": {
            "id": 1,
            "status": "Activo",
            "operands": null,
            "operators": null,
            "createAt": "2019-05-21"
        },
        "createAt": "2019-05-21"
    },
    {
        "id": 2,
        "value": 3.5,
        "status": "Activo",
        "sessionId": {
            "id": 1,
            "status": "Activo",
            "operands": null,
            "operators": null,
            "createAt": "2019-05-21"
        },
        "createAt": "2019-05-21"
    },
    {
        "id": 3,
        "value": 3.5,
        "status": "Activo",
        "sessionId": {
            "id": 1,
            "status": "Activo",
            "operands": null,
            "operators": null,
            "createAt": "2019-05-21"
        },
        "createAt": "2019-05-21"
    }
]
```

# Operadores

    GET /consultar/operadores
    
Retorna los operadores.

## Parameters
N/A

## Example
### Request

    http://localhost:8080/consultar/operadores

### Response
``` json
[
    {
        "id": 1,
        "value": "resta",
        "status": "Inactivo",
        "sessionId": {
            "id": 1,
            "status": "Activo",
            "operands": null,
            "operators": null,
            "createAt": "2019-05-21"
        },
        "createAt": "2019-05-21"
    }
]
```
# Logs

    GET /consultar/logs
    
Retorna los logs.

## Parameters
N/A

## Example
### Request

    http://localhost:8080/consultar/logs

### Response
``` json
[
    {
        "id": 1,
        "severity": "INFO",
        "message": "Iniciando Request",
        "request": "http://localhost:8080/calculatorApi/getSessionId",
        "logger": "com.caforerof.calculator.controller.SessionIdController",
        "dated": "2019-05-21",
        "sessionId": 0,
        "method": "getSession"
    },
    {
        "id": 2,
        "severity": "INFO",
        "message": "Iniciando Request",
        "request": "http://localhost:8080/calculatorApi/addOperand/3.5/1",
        "logger": "com.caforerof.calculator.controller.OperandController",
        "dated": "2019-05-21",
        "sessionId": 0,
        "method": "addOperand"
    },
    {
        "id": 3,
        "severity": "INFO",
        "message": "Iniciando Request",
        "request": "http://localhost:8080/calculatorApi/setOperator/resta/1",
        "logger": "com.caforerof.calculator.controller.OperatorController",
        "dated": "2019-05-21",
        "sessionId": 0,
        "method": "setOperator"
    }
]
```
