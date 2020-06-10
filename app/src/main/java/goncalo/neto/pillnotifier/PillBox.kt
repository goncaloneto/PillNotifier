package goncalo.neto.pillnotifier

import java.text.SimpleDateFormat
import java.util.*

public class PillBox(name : String, quantity : Int, remaining: Int, expirationDate : Date)
{
    private var name = name
    private var quantity = quantity
    private var expirationDate: Date = expirationDate

    private var remaining : Int = remaining

    public var Quantity : Int
        get() = quantity
        set(value) {quantity = value}

    public var RemainingPills : Int
        get() = remaining
        set(value) {remaining = value}

    public var Name : String
        get() = name
        set(value) {name = value}

    public var ExpirationDate : String = SimpleDateFormat("dd/MM/yyyy").format(expirationDate)
}