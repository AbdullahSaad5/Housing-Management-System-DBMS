package backend;


public class Utilities {

	public static boolean checkString(String message) {
		for (int i = 0; i < message.length(); i++) {
			char curr = message.charAt(i);
			if (!Character.isLetter(curr)) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkStringWithSpaces(String message) {
		for (int i = 0; i < message.length(); i++) {
			char curr = message.charAt(i);
			if (!(Character.isAlphabetic(curr) || Character.isSpaceChar(curr) || Character.isDigit(curr))) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkNumber(String message) {
		for (int i = 0; i < message.length(); i++) {
			char curr = message.charAt(i);
			if (!Character.isDigit(curr)) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkFloat(String message) {
		String part1 = "";
		int index = -1;
		for (int i = 0; i < message.length(); i++) {
			char curr = message.charAt(i);
			if (curr == '.') {
				index = i;
				break;
			}
			part1 += curr;
		}
		if (index == -1 || index == message.length() - 1) {
			return false;
		}
		String part2 = message.substring(index + 1);
		if (checkNumber(part1) && checkNumber(part2)) {
			return true;
		}
		return false;
	}


	public static boolean checkEmail(String message) {
		String part1 = "";
		int index = -1;
		for (int i = 0; i < message.length(); i++) {
			char curr = message.charAt(i);
			if (curr == '@') {
				index = i;
				break;
			}
			part1 += curr;
		}

		if (part1.length() < 1) {
			return false;
		}
		if (index == -1) {
			return false;
		}

		String part2 = message.substring(index + 1);

		for (int i = 0; i < part1.length(); i++) {
			if (!Character.isLetterOrDigit(message.charAt(i))) {
				return false;
			}
		}
		if (part2.equalsIgnoreCase("gmail.com")) {
			return true;
		}
		return false;
	}
	
	
	public static boolean checkUsername(String username) {
		for (int i = 0; i < username.length(); i++) {
			char curr = username.charAt(i);
			if (!Character.isLetterOrDigit(curr)) {
				return false;
			}
		}
		return true;
	}
	

	public static boolean checkPassword(String password) {
		if (password.length() <= 6) {
			return false;
		}
		if (password.toLowerCase().equals(password)) {
			return false;
		}
		if (password.toUpperCase().equals(password)) {
			return false;
		}
		for (int i = 0; i < password.length(); i++) {
			char curr = password.charAt(i);
			if (!Character.isLetterOrDigit(curr)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean matchPasswords(String password1, String password2) {
		return (password1.equals(password2));
	}
}


