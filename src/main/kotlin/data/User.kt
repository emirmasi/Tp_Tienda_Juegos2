package data

data class User(
    val id: Long,
    val nickName: String,
    val password: String,
    val name: String,
    val surname: String,
    val money: Double,
    val createdDate: String
){
    override fun toString(): String {
        return "User(id=$id, nickName='$nickName', password='$password', name='$name', surname='$surname', money=$money, createdDate='$createdDate')"
    }

}
