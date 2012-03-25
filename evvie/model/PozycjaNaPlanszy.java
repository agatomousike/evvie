package evvie.model;

public enum PozycjaNaPlanszy {
	P0,P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,
	P11,P12,P13,P14,P15,P16,P17,P18,P19,P20,
	P21,P22,P23,P24,P25,P26,P27,P28,P29,P30,
	P31,P32,P33,P34,P35,P36;
	/*pole P0 - klocek nie jest widoczny na planszy*/
	private static final int LICZBA_POL_PLANSZY = 36;
	
	public static int getLICZBA_POL_PLANSZY() {
		return LICZBA_POL_PLANSZY;
	}
	
	public int getValue()
	{
		return getValue(this);
	}
	
	public static int getValue(PozycjaNaPlanszy pozycja){
		switch(pozycja){
		case P0:
			return 0;
		case P1:
			return 1;
		case P2:
			return 2;
		case P3:
			return 3;
		case P4:
			return 4;
		case P5:
			return 5;
		case P6:
			return 6;
		case P7:
			return 7;
		case P8:
			return 8;
		case P9:
			return 9;
		case P10:
			return 10;
		case P11:
			return 11;
		case P12:
			return 12;
		case P13:
			return 13;
		case P14:
			return 14;
		case P15:
			return 15;
		case P16:
			return 16;
		case P17:
			return 17;
		case P18:
			return 18;
		case P19:
			return 19;
		case P20:
			return 20;
		case P21:
			return 21;
		case P22:
			return 22;
		case P23:
			return 23;
		case P24:
			return 24;
		case P25:
			return 25;
		case P26:
			return 26;
		case P27:
			return 27;
		case P28:
			return 28;
		case P29:
			return 29;
		case P30:
			return 30;
		case P31:
			return 31;
		case P32:
			return 32;
		case P33:
			return 33;
		case P34:
			return 34;
		case P35:
			return 35;
		case P36:
			return 36;
		}
		return 0;
	}
}
