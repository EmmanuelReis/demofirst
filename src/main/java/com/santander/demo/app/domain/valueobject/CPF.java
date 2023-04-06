package com.santander.demo.app.domain.valueobject;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

public class CPF extends Document {

    public CPF(String document) {
        super(document);
    }

    @Override
	public String formatted() {
		String mascara = "###.###.###-##";
		try {

			MaskFormatter mask = new MaskFormatter(mascara);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(onlyNumbers());
		}
		catch(ParseException e) {
			e.printStackTrace();
		}

        return "";
	}
    
    @Override
    public boolean isValid() {
        Pattern pattern = Pattern.compile("^(\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$");
        Matcher matcher = pattern.matcher(document);
		
		if(!matcher.matches())
			return false;

		String onlyNumbers = onlyNumbers();

		// considera-se erro document's formados por uma sequencia de numeros iguais
		if (onlyNumbers.equals("00000000000") || onlyNumbers.equals("11111111111") || onlyNumbers.equals("22222222222")
				|| onlyNumbers.equals("33333333333") || onlyNumbers.equals("44444444444") || onlyNumbers.equals("55555555555")
				|| onlyNumbers.equals("66666666666") || onlyNumbers.equals("77777777777") || onlyNumbers.equals("88888888888")
				|| onlyNumbers.equals("99999999999") || (onlyNumbers.length() != 11))
			return false;

		char dig10, dig11;
		int sm, i, r, num, weight;

		// Calculo do 1o. Digito Verificador
		sm = 0;
		weight = 10;
		for (i = 0; i < 9; i++) {
			// converte o i-esimo caractere do doc em um numero:
			// por exemplo, transforma o caractere '0' no inteiro 0
			// (48 eh a posicao de '0' na tabela ASCII)
			num = (int) (onlyNumbers.charAt(i) - 48);
			sm = sm + (num * weight);
			weight = weight - 1;
		}

		r = 11 - (sm % 11);
		if ((r == 10) || (r == 11))
			dig10 = '0';
		else
			dig10 = (char) (r + 48); // converte no respectivo caractere numerico

		// Calculo do 2o. Digito Verificador
		sm = 0;
		weight = 11;
		for (i = 0; i < 10; i++) {
			num = (int) (onlyNumbers.charAt(i) - 48);
			sm = sm + (num * weight);
			weight = weight - 1;
		}

		r = 11 - (sm % 11);
		if ((r == 10) || (r == 11))
			dig11 = '0';
		else
			dig11 = (char) (r + 48);

		// Verifica se os digitos calculados conferem com os digitos informados.
		if ((dig10 == onlyNumbers.charAt(9)) && (dig11 == onlyNumbers.charAt(10)))
			return true;
		
		return false;
    }
}
