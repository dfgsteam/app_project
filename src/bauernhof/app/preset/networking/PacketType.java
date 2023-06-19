package bauernhof.preset.networking;

enum PacketType {
	INIT,
	REQUEST,
	UPDATE,
	GETSCORE,
	GETNAME,
	VERIFYGAME,
	KEEPALIVE,
	ERROR;
}
