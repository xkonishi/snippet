package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page;

import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC2030.service.CC2030S01.Condition;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.CC3030C01;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree.ExDefaultNode;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree.ExDefaultProvider;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.tree.ExDefaultNestedTree;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.service.CC3030S01;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.service.CC3030S01Mockup;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.dto.LoginModel;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.page.BasePage;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.session.AppSession;
import jp.co.canonits.prognerex.core.common.exception.LogicalException;
import jp.co.canonits.prognerex.core.presentation_wicket.component.ExFieldSet;
import jp.co.canonits.prognerex.core.presentation_wicket.component.ExTextField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CC3030C01 extends BasePage {

    /**
     * ログインスタンス
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CC3030C01.class);

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // 内部定数
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // 内部変数
    // -------------------------------------------------------------------------

    /**
     * ツリー部パネル
     */
    protected ExFieldSet pnlTree;

    /**
     * ツリー
     */
    protected ExDefaultNestedTree tree;

    /**
     * 詳細部パネル
     */
    protected ExFieldSet pnlDetail;

    /**
     * カット紙用
     */
    protected ExTextField<String> txtCutPrinter;

    /**
     * 連続紙用
     */
    protected ExTextField<String> txtDotPrinter;

    /**
     * ラベル用
     */
    protected ExTextField<String> txtLabelPrinter;

    /**
     * <p>起動条件 : コンストラクタ</p>
     * <p>処理概要 : 画面レイアウトを生成する</p>
     */
	public CC3030C01() {

        // --------------------------------------------------
        // プログラム情報
        // --------------------------------------------------

        // 処理ID
        setOperationId("CC3020");
        // プログラムID
        setProgramId("CC3020C01");
        // プログラム名
        setProgramName(this.getString("programName"));
        // プログラムリビジョン
        setProgramRevision("01.00");

        // --------------------------------------------------
        // アプリケーション生成
        // --------------------------------------------------

        // 画面タイトルを設定
        this.setTitle();
        // 画面コンポーネントを生成
        this.initializeComponents();
        // 初期化処理を実行
        this.initializeEvent();
	}

    /**
     * <p>起動条件 : サービス呼出し時</p>
     * <p>処理概要 : サービスを生成し返却する</p>
     * 
     * @return サービス
     */
    protected CC3030S01 getService(){

        CC3030S01 service = null;
        AppSession session = (AppSession)this.getSession();
        LoginModel loginModel = session.getLoginModel();

        if(this.isMockupMode()){
            service = new CC3030S01Mockup();
        }else{
            service = new CC3030S01();
        }

        service.setLoginModel(loginModel);
        service.setClientLocale(this.getLocale());
        return service;
    }

    /**
     * <p>起動条件 : 初期処理時</p>
     * <p>処理概要 : 画面コンポーネントを生成する</p>
     * 
     */
    protected void initializeComponents(){

        // --------------------------------------------------
        // ツリー部の生成
        // --------------------------------------------------
        // ツリー部パネル
        this.pnlTree = new ExFieldSet("pnlTree");
        this.getForm().add(this.pnlTree);
        // ツリー
        tree = new ExDefaultNestedTree("tree", new ExDefaultProvider() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(ExDefaultNode node) {
                CC3030C01.this.setPrinterSetting(node);
            }
        });
        this.pnlTree.add(tree);
         
        // --------------------------------------------------
        // 詳細部の生成
        // --------------------------------------------------
        // 詳細部パネル
        this.pnlDetail = new ExFieldSet("pnlPrinter");
        this.getForm().add(this.pnlDetail);
        // カット紙用
        this.txtCutPrinter = new ExTextField<String>("txtCutPrinter", new Model<String>());
        this.pnlDetail.add(this.txtCutPrinter);
        // 連続紙用
        this.txtDotPrinter = new ExTextField<String>("txtDotPrinter", new Model<String>());
        this.pnlDetail.add(this.txtDotPrinter);
        // ラベル用
        this.txtLabelPrinter = new ExTextField<String>("txtLabelPrinter", new Model<String>());
        this.pnlDetail.add(this.txtLabelPrinter);

        // --------------------------------------------------
        // ファンクション部のラベル設定
        // --------------------------------------------------

        this.setFunction03Label("");
        this.setFunction04Label("");
        this.setFunction05Label("");
        this.setFunction06Label("");
        this.setFunction07Label("");
        this.setFunction08Label("");
        this.setFunction12Label("");
    }

    /**
     * <p>先頭項目にフォーカスを設定する</p>
     * 
     */
    protected void setFocusToFirstItem(){

        // ツリー部

    }

    /**
     * <p>起動条件 : 初期処理時</p>
     * <p>処理概要 : 本処理を実行する</p>
     * 
     * @return 初期処理の成否
     */
    @Override
    protected boolean executeInit(){
        boolean ret = false;

        // --------------------------------------------------
        // 初期処理の実行
        // --------------------------------------------------

        try{
            // サービスの取得
            CC3030S01 service = this.getService();

            // 引数(OUT)の設定
            List<CC3030S01.Result> results = new ArrayList<CC3030S01.Result>();

            // サービスの実行
            ret = service.executeInit(results);

            // 実行結果の表示
            if(!ret){
                this.showMessage(service.getMessageModel());

            }else{

                // --------------------------------------------------
                // ツリー部の設定
                // --------------------------------------------------

                this.setTreeData(results);
                ret = true;
            }

        }catch(LogicalException e){

            // エラーログ出力(システムエラー)
            LOGGER.error("Printer Setting Search service initialization is failed. Some runtime exception happen. Please check stacktrace.", e);
            
            throw new IllegalStateException(e);
        }

        return ret;
    }

    /**
     * <p>起動条件 : 初期処理時</p>
     * <p>処理概要 : 後処理を実行する</p>
     * 
     * @return 初期処理における後処理の成否
     */
    @Override
    protected boolean postInit(){

        this.tree.expandAll();

        // --------------------------------------------------
        // ファンクション部
        // --------------------------------------------------

        // [F02]メニュー
        this.setFunction02Enabled(true);

        // --------------------------------------------------
        // フォーカス設定
        // --------------------------------------------------

        this.setFocusToFirstItem();

        return true;
    }
    
    /**
     * <p>起動条件 : 初期処理時</p>
     * <p>処理概要 : 検索結果をツリーに反映する。</p>
     * 
     * @param details 検索結果
     */
    protected void setTreeData(List<CC3030S01.Result> results) {
        ExDefaultProvider provider = (ExDefaultProvider)this.tree.getProvider();
        provider.setNodeList(DummyList.getList());
        
        List<ExDefaultNode> nodes = new ArrayList<>();
        Iterator<CC3030S01.Result> it = results.iterator();
        while(it.hasNext()) {
            CC3030S01.Result r = it.next();
            ExDefaultNode n = new ExDefaultNode(r.userId){
                private static final long serialVersionUID = 1L;
                public CC3030S01.Result result = r;
            };
            int index = nodes.indexOf(n);
            if (index != -1) {
                n = nodes.get(index);
            }
            else {
                nodes.add(n);
            }
            new ExDefaultNode(n, r.ipAddress);
        }
        provider.setNodeList(nodes);
    }
    
    protected void setPrinterSetting(ExDefaultNode node) {
        boolean ret = false;

        List<String> list = node.splitFullLabel();
        if (list.size() == 2) {

            try{
                // サービスの取得
                CC3030S01 service = this.getService();

                // 条件(IN)の設定
                CC3030S01.Condition condition = service.new Condition();
                condition.userId = list.get(0);
                condition.ipAddress = list.get(1);

                // 引数(OUT)の設定
                List<CC3030S01.Result> results = new ArrayList<CC3030S01.Result>();

                // サービスの実行
                ret = service.executeSearch(condition, results);

                // 実行結果の表示
                if(!ret){
                    this.showMessage(service.getMessageModel());

                }else{

                    // --------------------------------------------------
                    // 詳細部の設定
                    // --------------------------------------------------

                    Iterator<CC3030S01.Result> it = results.iterator();
                    if (it.hasNext()) {
                        CC3030S01.Result r = it.next();
                        this.txtCutPrinter.setModelObject(r.cpPrinterName);
                        this.txtDotPrinter.setModelObject(r.dpPrinterName);
                        this.txtLabelPrinter.setModelObject(r.lpPrinterName);
                    }
                    ret = true;
                }

            }catch(LogicalException e){

                // エラーログ出力(システムエラー)
                LOGGER.error("Printer Setting Search service initialization is failed. Some runtime exception happen. Please check stacktrace.", e);
                
                throw new IllegalStateException(e);
            }
        }
        else {
            
        }
    }
}
