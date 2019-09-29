public class CallProgram {

    private final String[] commands = {
            "javac ",
            ""
    };

    private String language;
    private String sourcecodeFileName;
    private String inputFileName;

    public CallProgram(String language, String sourcecodeFileName, String inputFileName) {
        this.language = language;
        this.sourcecodeFileName = sourcecodeFileName;
        this.inputFileName = inputFileName;
    }

    public void executeJavaProgram() throws Exception{
        Runtime.getRuntime().exec("javac " + sourcecodeFileName + "." + language).waitFor();
        Runtime.getRuntime().exec("java " + sourcecodeFileName);
    }

    public void executeCppProgram() {
    }

    public void executeCsProgram() {
    }

    public static void main(String[] args) {
        String language = args[0];
        String sourcecodeFileName = args[1];
        String inputFileName = args[2];

        CallProgram callProgram = new CallProgram(language, sourcecodeFileName, inputFileName);

        try {
            if ("java".equals(language)) {
                callProgram.executeJavaProgram();
            } else if ("cpp".equals(language)) {
                callProgram.executeCppProgram();
            } else if ("cs".equals(language)) {
                callProgram.executeCsProgram();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
