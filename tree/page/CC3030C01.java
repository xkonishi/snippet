package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.page.CC3030C01;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.service.CC3030S01;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.service.CC3030S01Mockup;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.dto.LoginModel;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.page.BasePage;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.session.AppSession;
import jp.co.canonits.prognerex.core.presentation_wicket.component.ExFieldSet;
import jp.co.canonits.prognerex.core.presentation_wicket.component.ExLabel;
import jp.co.canonits.prognerex.core.presentation_wicket.component.ExTextField;

import java.util.Set;

public class CC3030C01 extends BasePage {

    /**
     * ログインスタンス
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CC3030C01.class);

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;
    
    
    NestedTree<Foo> tree;
//    TreeContent<Foo> content;
    SelectableFolderContent content;
    
    /* expandAll と collapseAll を動作させるために状態を持つ FooExpansion を返す */
    private class FooExpansionModel extends AbstractReadOnlyModel<Set<Foo>>{
        private static final long serialVersionUID = 1L;

    @Override
       public Set<Foo> getObject(){
          return FooExpansion.get();
       }
    }

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
//        this.pnlTree.add(new DefaultNestedTree<Foo>("tree", new FooProvider()){
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            protected Component newContentComponent(String id, IModel<Foo> node)
//            {
//                return super.newContentComponent(id, node);
//            }
//        });
        
        
        FooProvider provider = new FooProvider();
        tree = new NestedTree<Foo>("tree", provider, new FooExpansionModel()){
            private static final long serialVersionUID = 1L;

            @Override
            protected Component newContentComponent(String _id, IModel<Foo> _model){
               return CC3030C01.this.newContentComponent(_id, _model);
            }
         };
         FooExpansion.get().expandAll();
         // Windowsデザインのテーマを使用するように設定
         tree.add(new WindowsTheme());

//         content = new TreeContent<Foo>(){
         content = new SelectableFolderContent(provider){
            private static final long serialVersionUID = 1L;

//            @Override
//            public Component newContentComponent(String id, AbstractTree<Foo> tree, IModel<Foo> model){
//                return new Folder<Foo>(id, tree, model){
//                    private static final long serialVersionUID = 1L;
//                    
//                    @Override
//                    protected MarkupContainer newLinkComponent(String _id, final IModel<Foo> _model){
//                      Foo foo = _model.getObject();
//                      if (tree.getProvider().hasChildren(foo)){
//                         // ツリー node をクリックした時
//                         return super.newLinkComponent(_id, _model);
//                      }
//                      // ツリー leaf をクリックした時の動作
//                      return new AjaxLink<Foo>(_id, _model){
//                        private static final long serialVersionUID = 1L;
//
//                        @Override
//                        public void onClick(AjaxRequestTarget arg0){
//                            // TODO 自動生成されたメソッド・スタブ
//                        }
//                      };
//                    }
//                };
//            }
         };
         
         this.pnlTree.add(tree);
         //this.pnlTree.add(new ExLabel("tree", "MMMM"));


         
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
    }
    
    protected Component newContentComponent(String id, IModel<Foo> model){
        return content.newContentComponent(id, tree, model);
     }
}
