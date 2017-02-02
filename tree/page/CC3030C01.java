package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.CC3030C01;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.service.CC3030S01;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.service.CC3030S01Mockup;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.dto.LoginModel;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.page.BasePage;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.session.AppSession;
import jp.co.canonits.prognerex.core.common.exception.LogicalException;
import jp.co.canonits.prognerex.core.presentation_wicket.component.ExFieldSet;
import jp.co.canonits.prognerex.core.presentation_wicket.component.ExTextField;
import jp.co.canonits.prognerex.core.presentation_wicket.component.tree.ExDefaultNestedTree;
import jp.co.canonits.prognerex.core.presentation_wicket.component.tree.ExDefaultNode;
import jp.co.canonits.prognerex.core.presentation_wicket.component.tree.ExDefaultProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>プリンタ設定情報画面</p>
 * 
 * @author Canon IT Solutions Inc. R&amp;D Center
 * @version 2.5
 */
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
        this.tree = new ExDefaultNestedTree("tree", new ExDefaultProvider() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(ExDefaultNode node, AjaxRequestTarget targetOptional) {
                try {
                    CC3030C01.this.setPrinterSetting(node, targetOptional);
                    
                } catch (LogicalException ex) {
                    CC3030C01.this.showMessage("CME00000");
                }
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

        this.setFocus(this.tree);
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

                List<ExDefaultNode> nodes = new ArrayList<>();

                String parentName = "";
                ExDefaultNode parent = null;
                for(CC3030S01.Result r : results) {
                    if (r.userId.compareTo(parentName) != 0) {
                        parentName = r.userId;
                        parent = new ExDefaultNode(parentName);
                        nodes.add(parent);
                    }
                    parent.add(new ExDefaultNode(r.ipAddress));
                }
                this.tree.getProvider().setNodeList(nodes);
                
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

        // ツリーを全て展開
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

    private void setPrinterSetting(ExDefaultNode node, AjaxRequestTarget targetOptional) throws LogicalException{

        // 詳細部パネルを更新対象に
        targetOptional.add(pnlDetail);

        // 親ノードの場合
        if (this.tree.getProvider().hasChildren(node)) {
            this.txtCutPrinter.setModelObject("");
            this.txtDotPrinter.setModelObject("");
            this.txtLabelPrinter.setModelObject("");
            return;
        }

        // 子ノードの場合
        // サービスの取得
        CC3030S01 service = this.getService();

        // 条件(IN)の設定
        CC3030S01.Condition condition = service.new Condition();
        condition.userId = node.getParent().getLabel();
        condition.ipAddress = node.getLabel();

        // 引数(OUT)の設定
        List<CC3030S01.Result> results = new ArrayList<CC3030S01.Result>();

        // サービスの実行
        boolean ret = service.executeSearch(condition, results);

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
        }
    }
}
