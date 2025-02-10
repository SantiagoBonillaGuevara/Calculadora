package com.example.calculator

class User {
    var nombre: String? = null
    var email: String? = null
    var uid: String? = null

    constructor() {}
    constructor(nombre: String?, email: String?, uid: String?) {
        this.nombre = nombre
        this.email = email
        this.uid = uid
    }
}