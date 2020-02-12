public class Date {

    String month, day, year;
    int intMonth, intDay, intYear, age;

    public Date(String date) {

        int loc1 = date.indexOf('/');
        month = date.substring(0, loc1);
        intMonth = Integer.parseInt(month);

        int loc2 = date.indexOf('/', 1 + loc1);
        day = date.substring(1 + loc1, loc2);
        intDay = Integer.parseInt(day);

        year = date.substring(1 + loc2);
        intYear = Integer.parseInt(year);
    }

    public String calculateAge(Date bd) {

        age = intYear - bd.getYear();

        if (intMonth - bd.getMonth() < 0) {

            age--;
        } else if (intMonth - bd.getMonth() == 0) {

            if (intDay - bd.getDay() < 0) {

                age--;
            }
        }

        return "You are " + age + " years old";
    }

    public String timeTilDate(Date d) {

        int monthsTilDate = intMonth - d.getMonth();
        int daysTilDate = intDay - d.getDay();

        if (monthsTilDate > 0) {

            monthsTilDate = 12 - monthsTilDate;
        } else if (monthsTilDate < 0) {

            monthsTilDate = Math.abs(monthsTilDate);
        }

        if (daysTilDate < 0) {

            daysTilDate = Math.abs(daysTilDate);
        } else if (daysTilDate > 0) {

            if (d.getMonth() == 1 || d.getMonth() == 2 || d.getMonth() == 4 || d.getMonth() == 6 || d.getMonth() == 8
                    || d.getMonth() == 9 || d.getMonth() == 11) {

                daysTilDate = 31 - daysTilDate;
            } else if (d.getMonth() == 3) {

                if (isLeapYear() == true) {

                    daysTilDate = 29 - daysTilDate;
                } else {

                    daysTilDate = 28 - daysTilDate;
                }
            } else {

                daysTilDate = 30 - daysTilDate;
            }

            monthsTilDate--;
        }

        if (monthsTilDate == 0 && daysTilDate == 0) {

            if (age == 16) {

                return "Today is your birthday!!! HAPPY BIRTHDAY!!!!!!!!!! Yay driving";
            } else if (age == 21) {

                return "Today is your birthday!!! HAPPY BIRTHDAY!!!!!!!!!! Now you can LEGALLY drink";
            } else {

                return "Today is your birthday!!! HAPPY BIRTHDAY!!!!!!!!!!";
            }
        }

        if (daysTilDate != 1) {

            return "Your birthday is in " + monthsTilDate + " months and " + daysTilDate + " days";
        } else {

            return "Your birthday is in " + monthsTilDate + " months and " + daysTilDate + " day";
        }
    }

    public boolean isLeapYear() {

        if ((intMonth >= 3 && (intYear + 1) % 4 == 0) || (intMonth < 4 && (intYear % 4) == 0)) {

            return true;
        } else {

            return false;
        }
    }

    public String eligibility() {

        String ableToDrink = "No", ableToDrive = "No";

        if (age >= 21) {

            ableToDrink = "Yes";
        }
        if (age >= 16) {

            ableToDrive = "Yes";
        }

        return "Able to drink? " + ableToDrink + "\nAble to drive? " + ableToDrive;
    }

    public int getMonth() {

        return intMonth;
    }

    public int getDay() {

        return intDay;
    }

    public int getYear() {

        return intYear;
    }

    public int getAge() {

        return age;
    }

    public String toString() {

        return month + "/" + day + "/" + year;
    }
}