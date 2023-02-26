package `in`.sateesh.ipac.util

import `in`.sateesh.ipac.data.model.User
import `in`.sateesh.ipac.data.model.UserResult

fun userResultToUser(userResult: UserResult) =
    User(
        name = getUserName(userResult),
        gender = userResult.gender,
        email = userResult.email,
        location = getLocation(userResult),
        username = userResult.login?.username,
        dobDate = userResult.dob?.date,
        age = userResult.dob?.age ?: 0,
        registerAge = userResult.registered?.age ?: 0,
        phone = userResult.phone,
        cell = userResult.cell,
        picture = userResult.picture?.large
    )


fun getUserName(userResult: UserResult) =
    "${userResult.name?.title ?: ""} ${userResult.name?.first ?: ""} ${userResult.name?.last ?: ""}"

fun getLocation(userResult: UserResult): String {
    var bufferString = StringBuffer("")

    userResult.location?.let {
        it.street?.let {
            bufferString.append(it.number)
            appendComma(bufferString)
            bufferString.append(it.name)
        }
        appendComma(bufferString)
        bufferString.append(it.city)
        appendComma(bufferString)
        bufferString.append(it.state)
        appendComma(bufferString)
        bufferString.append(it.country)
        appendComma(bufferString)
        bufferString.append(it.postcode)
    }
    return bufferString.toString()
}

fun appendComma(buffer: StringBuffer) {
    if (buffer.length > 0) {
        buffer.append(", ")
    }
}
