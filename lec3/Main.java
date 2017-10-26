// ワーキングメモリ
class WM {
    /*
     * WM要素
     * @param timeTag タイムタグ
     * @param name 名前
     * @param has ボールの状態
     *            ボールを持っている場合true
     *                    持っていない場合false
     * @param nextHas ボールの次状態(規則の適用するため)
     */
    private int timeTag; 
    private String name; 
    private boolean has; 
    private boolean nextHas;

    // タイムタグの設定
    public void setTimeTag(){
        this.timeTag = Main.maxTimeTag;
        Main.maxTimeTag++;
    }

    // 名前の設定
    public void setName(String name){
        this.name = name;
    }

    // ボールの状態の設定
    public void setHas(boolean has){
        this.has = has;
        this.nextHas = has;
    }

    // ボールの状態の変更
    public void setHas(){
        if(this.has)
            this.has = false;
        else
            this.has = true;
    }

    // ボールの次状態の設定
    public void setNextHas(boolean nextHas){
        this.nextHas = nextHas;
    }

    // クラスpersonの設定
    public void setPerson(String name, boolean has){
        this.setTimeTag();
        this.setName(name);
        this.setHas(has);
    }

    // タイムタグの取得
    public int getTimeTag(){
        return this.timeTag;
    }

    // 名前の取得
    public String getName(){
        return this.name;
    }

    // ボールの状態の取得
    public boolean getHas(){
        return this.has;
    }

    // ボールの次状態の取得
    public boolean getNextHas(){
        return this.nextHas;
    }

    // クラスの内容の表示
    public void show(){
        System.out.println(this.timeTag + ":(person ^name " + this.name + " ^has "+ this.has + ")");
    }
}

// 規則
class Rule {
    // 判定用クラスの設定
    WM personA = new WM();
    WM personB = new WM();
    boolean nextHas;

    public void setRule(String pName, boolean pHas, boolean pNextHas,String qName, boolean qHas, boolean qNextHas){
        this.personA.setName(pName);
        this.personB.setName(qName);
        this.personA.setHas(pHas);
        this.personB.setHas(qHas);
        this.personA.setNextHas(pNextHas);
        this.personB.setNextHas(qNextHas);
    }

    // アクション
    public void modify(WM person1, WM person2){
        // 次の条件を満たせばアクションを起こす
        if(person1.getName() == this.personA.getName() && person2.getName() == this.personB.getName())
            if(person1.getHas() == this.personA.getHas() && person2.getHas() == this.personB.getHas()){
                person1.setHas(this.personA.getNextHas());
                person2.setHas(this.personB.getNextHas());
                person1.setTimeTag();
                person2.setTimeTag();
            }
    }
}


public class Main {
    // 次に適用されるタイムタグ
    static int maxTimeTag = 1;

    public static void main(String[] args) {
        initTimeTag();

        WM[] P = new WM[4];
        
        // 初期状態の設定
        WM A = new WM();
        A.setPerson("A", true);
        WM B = new WM();
        B.setPerson("B", false);
        WM C = new WM();
        C.setPerson("C", true);
        WM D = new WM();
        D.setPerson("D", false);

        P[0] = A;
        P[1] = B;
        P[2] = C;
        P[3] = D;

        // 初期状態の確認
        Main.show(P);

        // 規則の生成
        Rule r1 = new Rule();
        r1.setRule("A", true, false, "B", false, true);
        Rule r2 = new Rule();
        r2.setRule("B", true, false, "C", false, true);
        Rule r3 = new Rule();
        r3.setRule("C", true, false, "D", false, true);

        // 規則の適用
        r3.modify(C, D);
        Main.show(P);
        r1.modify(A, B);
        Main.show(P);
        r2.modify(B, C);
        Main.show(P);
    }

    // タイムタグの設定
    static void initTimeTag(){
        maxTimeTag = 1;
    }

    // クラスの状態の表示
    static void show(WM[] P){
        for(int i=0, l = P.length; i<l; i++)
            P[i].show();
        System.out.println();
    }
}
