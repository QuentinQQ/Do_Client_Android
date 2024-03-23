@Composable
fun UserInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(48.dp).clip(CircleShape)
            )
            Spacer(Modifier.width(8.dp))
            Column {
                Text(text = "QuentinQ", fontWeight = FontWeight.Bold)
                Text(text = "用户ID 7894495 普通会员")
            }
            Spacer(Modifier.weight(1f)) // Use Spacer with weight to push the following content to the end of the Row
            // Replace with actual crown icon for 精英 PRO
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Elite Pro",
                tint = Color.Yellow
            )
        }
    }
}
