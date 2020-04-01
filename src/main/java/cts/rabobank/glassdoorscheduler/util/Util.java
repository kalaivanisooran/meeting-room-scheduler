package cts.rabobank.glassdoorscheduler.util;

public class Util {

        public static int setNoOfRecursiveBasedOnMode(String mode){
                int noOfRecurrsive;

                switch (mode) {
                        case "week":
                                noOfRecurrsive = 7;
                                break;
                        case "month":
                                noOfRecurrsive = 30;
                                break;
                        default:
                                noOfRecurrsive = 1;
                                break;
                }
                return noOfRecurrsive;
        }

}
