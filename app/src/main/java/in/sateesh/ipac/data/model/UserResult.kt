package `in`.sateesh.ipac.data.model

data class UserResult(
    var cell: String?,
    var dob: Dob?,
    var email: String?,
    var gender: String?,
    var id: Id?,
    var location: Location?,
    var login: Login?,
    var name: Name?,
    var phone: String?,
    var picture: Picture?,
    var registered: Registered?
)