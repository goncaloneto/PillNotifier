package goncalo.neto.pillnotifier

import java.text.SimpleDateFormat
import java.util.*

public class PillBox(name : String, quantity : Int, dosage: Float, remaining: Float, expirationDate: Date, startDate: Date)
{
    private var name = name
    private var quantity = quantity
    private var expirationDate: Date = expirationDate

    private var remaining : Float = remaining
    private var dosage : Float = dosage

    public var Quantity : Int
        get() = quantity
        set(value) {quantity = value}

    public var RemainingPills : Float
    get() = remaining
    set(value) {remaining = value}

    public var Dosage : Float
        get() = dosage
        set(value) {dosage = value}

    public var Name : String
        get() = name
        set(value) {name = value}

    public var ExpirationDate : String = SimpleDateFormat("dd/MM/yyyy").format(expirationDate)
    public var StartDate : String = SimpleDateFormat("dd/MM/yyyy").format(startDate)

}