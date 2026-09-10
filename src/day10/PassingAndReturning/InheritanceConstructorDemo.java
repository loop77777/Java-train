package day10.PassingAndReturning;

class InheritanceConstructorDemo {

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("     TRIGGERING MULTI-LEVEL INHERITANCE DEMO");
        System.out.println("=================================================\n");

        // 1. Creating GrandFather object
        System.out.println("--- Step 1: Creating GrandFather Object ---");

        GrandFather gfObject = new GrandFather("Murugan");

        System.out.println("GrandFather Name directly from object: " + gfObject.getGrandFatherName());

        System.out.println("-------------------------------------------\n");


        // 2. Creating Father object
        System.out.println("--- Step 2: Creating Father Object ---");

        Father fObject = new Father("Murugan", "Anand");

        System.out.println("Father Name from object: " + fObject.getFatherName());

        System.out.println("GrandFather Name via Father object: " + fObject.getGrandFatherName());

        System.out.println("---------------------------------------\n");


        // 3. Creating Son object
        System.out.println("--- Step 3: Creating Son Object ---");

        Son sonObject = new Son("Murugan", "Anand", "Karthik");

        sonObject.displayFamilyTree();

        System.out.println("=================================================");
    }


    // ============================================
    // GRAND FATHER
    // ============================================

    static class GrandFather {

        private String grandFatherName;

        GrandFather(String grandFatherName) {

            this.grandFatherName = grandFatherName;

            System.out.println("GrandFather Class Constructor");
        }

        public String getGrandFatherName() {
            return grandFatherName;
        }
    }


    // ============================================
    // FATHER
    // ============================================

    static class Father extends GrandFather {

        private String fatherName;

        Father(String grandFatherName, String fatherName) {

            super(grandFatherName);

            this.fatherName = fatherName;

            System.out.println("Father Class Constructor");
        }

        public String getFatherName() {
            return fatherName;
        }
    }


    // ============================================
    // SON
    // ============================================

    static class Son extends Father {

        private String sonName;

        Son(String grandFatherName, String fatherName, String sonName) {

            super(grandFatherName, fatherName);

            this.sonName = sonName;

            System.out.println("Son Class Constructor");
        }

        public void displayFamilyTree() {

            System.out.println("\n--- Family Tree ---");

            System.out.println("GrandFather : " + getGrandFatherName());

            System.out.println("Father      : " + getFatherName());

            System.out.println("Son         : " + sonName);
        }
    }
}