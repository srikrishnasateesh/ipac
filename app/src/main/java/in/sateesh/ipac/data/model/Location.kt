package `in`.sateesh.ipac.data.model

data class Location(
    var city: String?,
    var country: String?,
    var postcode: String?,
    var state: String?,
    var street: Street?
)