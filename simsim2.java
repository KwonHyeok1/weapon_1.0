package jwbook;

import java.util.Scanner;
import java.util.Random;

public class simsim2 {

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        Random z = new Random();
        String playAgain;
        int weaponLevel = 1;
        int reinforcementCount = 0;

        do {
            System.out.println("무기 강화하기\n");
            System.out.println("당신의 무기는 현재 +" + weaponLevel + " 입니다.");
            System.out.println("강화 하시겠습니까?(성공 확률: " + getSuccessRate(weaponLevel) + "%) \n\nY_예, N_아니오");

            var input = x.nextLine();

            if (input.equals("Y") || input.equals("y")) {
                int successRate = getSuccessRate(weaponLevel);
                int result = z.nextInt(100) + 1;

                if (result <= successRate) {
                    weaponLevel++;
                    System.out.println("강화 성공 (+" + weaponLevel + ")");
                } else {
                    if (weaponLevel >= 11) {
                        // +11부터 실패 확률 절반은 유지, 절반은 -1씩 감소
                        int half = getSuccessRate(weaponLevel) / 2;
                        int decrease = Math.max(0, weaponLevel - 10);
                        int failureRate = half - decrease;
                        if (result <= failureRate) {
                            System.out.println("무기 터짐! 강화 종료");
                            break;
                        } else {
                            if (weaponLevel == 15 || weaponLevel == 20 || weaponLevel == 25) {
                                System.out.println("강화 실패! 강화 수치는 유지됩니다. (+" + weaponLevel + ")");
                            } else {
                                weaponLevel = Math.max(weaponLevel - 1, 10);
                                System.out.println("강화 실패! 강화 수치가 1 감소되었습니다. (+" + weaponLevel + ")");
                            }
                        }
                    } else {
                        System.out.println("강화 실패");
                    }

                    if (weaponLevel == 27 && result <= 1) {
                        // +27은 1%로 무기가 터짐
                        System.out.println("무기 터짐! 강화 종료");
                        break;
                    } else if (weaponLevel == 28 && result <= 3) {
                        // +28은 3%로 무기가 터짐
                        System.out.println("무기 터짐! 강화 종료");
                        break;
                    } else if (weaponLevel == 29 && result <= 5) {
                        // +29는 5%로 무기가 터짐
                        System.out.println("무기 터짐! 강화 종료");
                        break;
                    }
                }

                reinforcementCount++;

                if (weaponLevel >= 30) {
                    System.out.println("무기가 +30까지 강화되었습니다. 최대 강화 수치에 도달했습니다.");
                    break;
                }
            } else {
                break;
            }

            System.out.println("다시 도전하시겠습니까? \n\nY_예, N_아니오");
            playAgain = x.nextLine();

            if (playAgain.equals("Y") || playAgain.equals("y")) {
                System.out.println("현재까지 강화 시도 횟수: " + reinforcementCount);
            }
        } while (playAgain.equals("Y") || playAgain.equals("y"));

        System.out.println("총 강화 시도 횟수: " + reinforcementCount);
    }

    private static int getSuccessRate(int level) {
        // 강화 레벨에 따른 성공 확률 반환
        if (level <= 3) {
            return 95;
        } else if (level <= 6) {
            return 90;
        } else if (level <= 9) {
            return 85;
        } else if (level <= 12) {
            return 75;
        } else if (level <= 15) {
            return 65;
        } else if (level <= 18) {
            return 50;
        } else if (level <= 21) {
            return 35;
        } else if (level <= 24) {
            return 20;
        } else if (level <= 27) {
            return 5;
        } else if (level <= 28) {
            return 3;
        } else {
            return 1;
        }
    }
}
