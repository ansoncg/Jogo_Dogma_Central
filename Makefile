all:
	javac -Xlint:unchecked codigo/jogo/*.java codigo/estados/*.java codigo/entidades/*.java codigo/graficos/*.java codigo/entrada/*.java codigo/visor/*.java
run:
	java codigo.jogo.Inicializador
	
