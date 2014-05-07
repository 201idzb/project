class DemoClass {
    public String demoMethod(String demoParam) {
        System.out.println("Parameter passed: " + demoParam);
         
        return DemoClass.class.getName();
    }
}