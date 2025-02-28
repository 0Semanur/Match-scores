import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment {
	static void isContains(String firstTeam, String secondTeam, ArrayList<String> team, ArrayList<String> score) {
		if (!team.contains(firstTeam)) {
			team.add(firstTeam);
			score.add("0" + "\t" + "0" + "\t" + "0" + "\t" + "0" + "\t" + "0:0" + "\t" + "0");
		}
		if (!team.contains(secondTeam)) {
			team.add(secondTeam);
			score.add("0" + "\t" + "0" + "\t" + "0" + "\t" + "0" + "\t" + "0:0" + "\t" + "0");
		}

	}
	static void printScreen(ArrayList<String> team, ArrayList<String> score) {
		for (int i = 0; i < team.size(); i++) {
			System.out.println(i+1+":\t"+team.get(i) + "  \t" + score.get(i));
		}
		System.out.println("_________________________________________________________________");
		System.out.println();
	}
	static void calculateFootballScore(String firstTeam, String secondTeam, String result, ArrayList<String> team,
			ArrayList<String> score) {
		String[] split = result.split(":");
		Integer firstScore = Integer.valueOf(split[0]);
		Integer secondScore = Integer.valueOf(split[1]);

		int indexOf = team.indexOf(firstTeam);
		int indexOf2 = team.indexOf(secondTeam);

		String[] firstTeamSplit = score.get(indexOf).split("\t");
		String[] secondTeamSplit = score.get(indexOf2).split("\t");

		int addingScoreFirst = 0;
		int addingScoreSecond = 0;

		if (firstScore > secondScore)
			addingScoreFirst += 3;
		else if (firstScore < secondScore)
			addingScoreSecond += 3;
		else {
			addingScoreFirst++;
			addingScoreSecond++;
		}

		String firstTeamResult = prepareScoreArray(firstTeamSplit, addingScoreFirst, firstScore, addingScoreFirst == 3,
				addingScoreFirst == 0, addingScoreFirst == 1, firstScore, secondScore);
		String secondTeamResult = prepareScoreArray(secondTeamSplit, addingScoreSecond, secondScore,
				addingScoreSecond == 3, addingScoreSecond == 0, addingScoreSecond == 1, secondScore, firstScore);
		score.set(indexOf, firstTeamResult);
		score.set(indexOf2, secondTeamResult);
	}

	static void calculateHandballScore(String firstTeam, String secondTeam, String result, ArrayList<String> team,
			ArrayList<String> score) {
		String[] split = result.split(":");
		Integer firstScore = Integer.valueOf(split[0]);
		Integer secondScore = Integer.valueOf(split[1]);

		int indexOf = team.indexOf(firstTeam);
		int indexOf2 = team.indexOf(secondTeam);

		String[] firstTeamSplit = score.get(indexOf).split("\t");
		String[] secondTeamSplit = score.get(indexOf2).split("\t");

		int addingScoreFirst = 0;
		int addingScoreSecond = 0;

		if (firstScore > secondScore)
			addingScoreFirst += 2;
		else if (firstScore < secondScore)
			addingScoreSecond += 2;
		else {
			addingScoreFirst++;
			addingScoreSecond++;
		}

		String firstTeamResult = prepareScoreArray(firstTeamSplit, addingScoreFirst, firstScore, addingScoreFirst == 2,
				addingScoreFirst == 0, addingScoreFirst == 1, firstScore, secondScore);
		String secondTeamResult = prepareScoreArray(secondTeamSplit, addingScoreSecond, secondScore,
				addingScoreSecond == 2, addingScoreSecond == 0, addingScoreSecond == 1, secondScore, firstScore);
		score.set(indexOf, firstTeamResult);
		score.set(indexOf2, secondTeamResult);
	}

	static void calculateBasketballScore(String firstTeam, String secondTeam, String result, ArrayList<String> team,
			ArrayList<String> score) {
		String[] split = result.split(":");
		Integer firstScore = Integer.valueOf(split[0]);
		Integer secondScore = Integer.valueOf(split[1]);

		int indexOf = team.indexOf(firstTeam);
		int indexOf2 = team.indexOf(secondTeam);

		String[] firstTeamSplit = score.get(indexOf).split("\t");
		String[] secondTeamSplit = score.get(indexOf2).split("\t");

		int addingScoreFirst = 0;
		int addingScoreSecond = 0;

		if (firstScore > secondScore) {
			addingScoreFirst += 2;
			addingScoreSecond++;
		} else {
			addingScoreFirst++;
			addingScoreSecond += 2;
		}

		String firstTeamResult = prepareScoreArray(firstTeamSplit, addingScoreFirst, firstScore, addingScoreFirst == 2,
				addingScoreFirst == 1, false, firstScore, secondScore);
		String secondTeamResult = prepareScoreArray(secondTeamSplit, addingScoreSecond, secondScore,
				addingScoreSecond == 2, addingScoreSecond == 1, false, secondScore, firstScore);
		score.set(indexOf, firstTeamResult);
		score.set(indexOf2, secondTeamResult);
	}

	static void calculateVolleyballScore(String firstTeam, String secondTeam, String result, ArrayList<String> team,
			ArrayList<String> score) {
		String[] split = result.split(":");
		Integer firstScore = Integer.valueOf(split[0]);
		Integer secondScore = Integer.valueOf(split[1]);

		int indexOf = team.indexOf(firstTeam);
		int indexOf2 = team.indexOf(secondTeam);

		String[] firstTeamSplit = score.get(indexOf).split("\t");
		String[] secondTeamSplit = score.get(indexOf2).split("\t");

		int addingScoreFirst = 0;
		int addingScoreSecond = 0;

		if (firstScore == 3 && (secondScore == 0 || secondScore == 1)) {
			addingScoreFirst += 3;
		} else if (firstScore == 3 && secondScore == 2) {
			addingScoreFirst += 2;
			addingScoreSecond++;
		} else if (secondScore == 3 && firstScore == 2) {
			addingScoreFirst++;
			addingScoreSecond += 2;
		} else {
			addingScoreSecond += 3;
		}

		String firstTeamResult = prepareScoreArray(firstTeamSplit, addingScoreFirst, firstScore, addingScoreFirst > 1,
				addingScoreFirst < 2, false, firstScore, secondScore);
		String secondTeamResult = prepareScoreArray(secondTeamSplit, addingScoreSecond, secondScore,
				addingScoreSecond > 1, addingScoreSecond < 2, false, secondScore, firstScore);
		score.set(indexOf, firstTeamResult);
		score.set(indexOf2, secondTeamResult);
	}

	static String prepareScoreArray(String[] teamSplit, int point, int score, boolean isWin, boolean isLoss,
			boolean isDraw, int layer1, int layer2) {
		Integer playedMatch = Integer.valueOf(teamSplit[0]) + 1;
		Integer win = Integer.valueOf(teamSplit[1]);
		win = isWin ? win + 1 : win;
		Integer evenScore = Integer.valueOf(teamSplit[2]);
		evenScore = isDraw ? evenScore + 1 : evenScore;
		Integer lost = Integer.valueOf(teamSplit[3]);
		lost = isLoss ? lost + 1 : lost;
		String[] split = teamSplit[4].split(":");
		Integer firstLayer = Integer.valueOf(split[0]);
		firstLayer += layer1;
		Integer secondLayer = Integer.valueOf(split[1]);
		secondLayer += layer2;
		Integer scored = Integer.valueOf(teamSplit[5]);
		scored = scored + point;

		return playedMatch + "\t" + win + "\t" + evenScore + "\t" + lost + "\t" + firstLayer + ":" + secondLayer + "\t"
				+ scored;
	}
	

	public static void main(String[] args) {
		 File footballFile = new File("football.txt");
	        File volleyballFile = new File("volleyball.txt");
	        File basketballFile = new File("basketball.txt");
	        File handballFile = new File("handball.txt");
	        
		ArrayList<String> football = new ArrayList<>();
		ArrayList<String> volleyball = new ArrayList<>();
		ArrayList<String> handball = new ArrayList<>();
		ArrayList<String> basketball = new ArrayList<>();

		ArrayList<String> footballScore = new ArrayList<>();
		ArrayList<String> volleyballScore = new ArrayList<>();
		ArrayList<String> handballScore = new ArrayList<>();
		ArrayList<String> basketballScore = new ArrayList<>();

		try {
			File myObj = new File("fixtures.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] split = data.split("\t");
				if (split[0].equals("F")) {
					isContains(split[1], split[2], football, footballScore);
					calculateFootballScore(split[1], split[2], split[3], football, footballScore);
				} else if (split[0].equals("V")) {
					isContains(split[1], split[2], volleyball, volleyballScore);
					calculateVolleyballScore(split[1], split[2], split[3], volleyball, volleyballScore);
				} else if (split[0].equals("B")) {
					isContains(split[1], split[2], basketball, basketballScore);
					calculateBasketballScore(split[1], split[2], split[3], basketball, basketballScore);
				} else if (split[0].equals("H")) {
					isContains(split[1], split[2], handball, handballScore);
					calculateHandballScore(split[1], split[2], split[3], handball, handballScore);
				}

			}
			myReader.close();
			
            writeToFile(footballFile, football, footballScore);
            writeToFile(volleyballFile, volleyball, volleyballScore);
            writeToFile(basketballFile, basketball, basketballScore);
            writeToFile(handballFile, handball, handballScore);

			System.out.println("Football data : ");
			printScreen(football, footballScore);
			System.out.println("Volleyball data : ");
			printScreen(volleyball, volleyballScore);
			System.out.println("Basketball data : ");
			printScreen(basketball, basketballScore);
			System.out.println("Handball data : ");
			printScreen(handball, handballScore);
		} catch (FileNotFoundException e) {
			System.out.println("File not exception");
		}
	}
	static void writeToFile(File file, ArrayList<String> teams, ArrayList<String> scores) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < teams.size(); i++) {
                writer.write(i + 1 + ":\t" + teams.get(i) + "  \t" + scores.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

