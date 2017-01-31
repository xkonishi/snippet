package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC1040.service.CC1040S01.Condition;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CC2000.service.ListService;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CX1010.service.CX1010S01.Choices;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.CX1010.service.CX1010S01.Detail;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.constants.AppConstants;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.dao.APTemplateGeneralJDBCUtilDao;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.dao.exception.APTemplateSQLException;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.dto.BaseServiceParameters;
import jp.co.canonits.prognerex.aptemplate_desktopaplike.service.BaseService;
import jp.co.canonits.prognerex.core.common.constants.PrognerCoreCommonConstants;
import jp.co.canonits.prognerex.core.common.exception.LogicalException;
import jp.co.canonits.prognerex.core.common.message.MessageManager;
import jp.co.canonits.prognerex.core.common.utility.NumberUtility;
import jp.co.canonits.prognerex.core.common.utility.resource.ResourceFileNotFoundException;
import jp.co.canonits.prognerex.core.dao.resultset.SimpleResultSet;

public class CC3030S01 extends BaseService {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ログインスタンス
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CC3030S01.class);

    /**
     * メッセージファイルクラスファイル
     */
    protected static final String messageFileClassPath = "jp/co/canonits/prognerex/aptemplate_desktopaplike/CC3030/service/CC3030S01";

    /**
     * メッセージファイルの読み込みを行います
     */
    static{
        try{
            MessageManager.loadMessages(messageFileClassPath, PrognerCoreCommonConstants.LANGAGE_LOCALE_LIST);
        }catch(ResourceFileNotFoundException e){
            throw new IllegalStateException(e);
        }
    }

    // --------------------------------------------------------------------------
    // 内部定数
    // --------------------------------------------------------------------------

    // ------------------------------------------------------
    // プリンタ区分
    // ------------------------------------------------------

    /**
     * カット紙
     */
    protected static final String PRINTERKBN_CUT = "CP";

    /**
     * 連続紙
     */
    protected static final String PRINTERKBN_DOT = "DP";

    /**
     * ラベル紙
     */
    protected static final String PRINTERKBN_LABEL = "LP";

    // ------------------------------------------------------
    // SQL
    // ------------------------------------------------------

    /**
     * プリンタ設定情報取得（全件）
     */
    protected final String SQL_SELECT_CCPRINTERSETTING_ALL =
        "SELECT USERID"
      +     " , IPADDRESS"
      +     " , MAX(CASE WHEN PRINTERKBN = 'CP' THEN PRINTERNM END) AS CP_PRINTERNM"
      +     " , MAX(CASE WHEN PRINTERKBN = 'DP' THEN PRINTERNM END) AS DP_PRINTERNM"
      +     " , MAX(CASE WHEN PRINTERKBN = 'LP' THEN PRINTERNM END) AS LP_PRINTERNM"
      +  " FROM CCPRINTERSETTING"
      + " GROUP BY USERID, IPADDRESS"
      + " ORDER BY USERID, IPADDRESS";

    /**
     * プリンタ設定情報取得
     */
    protected final String SQL_SELECT_CCPRINTERSETTING =
        "SELECT USERID"
      +     " , IPADDRESS"
      +     " , MAX(CASE WHEN PRINTERKBN = 'CP' THEN PRINTERNM END) AS CP_PRINTERNM"
      +     " , MAX(CASE WHEN PRINTERKBN = 'DP' THEN PRINTERNM END) AS DP_PRINTERNM"
      +     " , MAX(CASE WHEN PRINTERKBN = 'LP' THEN PRINTERNM END) AS LP_PRINTERNM"
      +  " FROM CCPRINTERSETTING"
      + " WHERE USERID = :USERID"
      +   " AND IPADDRESS = :IPADDRESS"
      + " GROUP BY USERID, IPADDRESS"
      + " ORDER BY USERID, IPADDRESS";
    // --------------------------------------------------------------------------
    // 公開クラス
    // --------------------------------------------------------------------------

    // --------------------------------------------------
    // I/Oパラメータ
    // --------------------------------------------------

    /**
     * 検索結果I/Oパラメータ
     * 
     * @author Canon IT Solutions Inc. R&amp;D Center
     * @version 2.5
     */
    public class Result extends BaseServiceParameters{

        /**
         * シリアルバージョンUID
         */
        private static final long serialVersionUID = 1L;

        /**
         * ユーザID
         */
        public String userId;

        /**
         * IPアドレス
         */
        public String ipAddress;

        /**
         * 連続紙用プリンタ名
         */
        public String cpPrinterName;

        /**
         * カット紙用プリンタ名
         */
        public String dpPrinterName;

        /**
         * ラベル紙用プリンタ名
         */
        public String lpPrinterName;
    }
    
    /**
     * 条件部
     * 
     * @author Canon IT Solutions Inc. R&amp;D Center
     * @version 2.5
     */
    public class Condition extends BaseServiceParameters{

        /**
         * シリアルバージョンUID
         */
        private static final long serialVersionUID = 1L;

        /**
         * ユーザID
         */
        public String userId;

        /**
         * IPアドレス
         */
        public String ipAddress;
        
    }

    /**
     * <p>起動条件 : コンストラクタ</p>
     * <p>処理概要 : サービスを生成</p>
     * 
     */
	public CC3030S01() {

        // --------------------------------------------------
        // プログラム情報
        // --------------------------------------------------

        // 処理ID
        this.setOperationId("CC3030");
        // プログラムID
        this.setProgramId("CC3030S01");
        // プログラム名
        setProgramName(MessageManager.getMessage(messageFileClassPath, "programName"));
        // プログラムリビジョン
        this.setProgramRevision("01.00");
	}

    /**
     * <p>起動条件 : 初期処理時</p>
     * <p>処理概要 : 初期処理を実行する</p>
     * 
     * @param results (O)検索結果
     * @return 初期処理の成否
     * @throws LogicalException 論理的例外
     */
    public boolean executeInit(List<Result> results) throws LogicalException{

        boolean ret = false;

        // --------------------------------------------------
        // 開始処理の実行
        // --------------------------------------------------
        if(!this.initializeService()){
            return false;
        }

        // --------------------------------------------------
        // 検索の実行
        // --------------------------------------------------
        APTemplateGeneralJDBCUtilDao dao = new APTemplateGeneralJDBCUtilDao();
        SimpleResultSet rs = null;
        try{
            // データベース接続
            dao.open();

            // SQLの設定
            dao.bindSQL(SQL_SELECT_CCPRINTERSETTING_ALL);

            // SQLの実行
            rs = dao.executeQuery();
            while(rs.next()){
                Result result = new Result();
                result.userId = rs.getString("USERID");
                result.ipAddress = rs.getString("IPADDRESS");
                result.cpPrinterName = rs.getString("CP_PRINTERNM");
                result.dpPrinterName = rs.getString("DP_PRINTERNM");
                result.lpPrinterName = rs.getString("LP_PRINTERNM");
                results.add(result);
            }

            // メッセージ表示(検索正常終了)
            this.setMessage("CMN00004", NumberUtility.toDisplayFormat(results.size()));
            // ログ出力(検索正常終了)
            LOGGER.info("Selecting CCPRINTERSETTING is success.");
            ret = true;

        }catch(APTemplateSQLException e){
            // エラーログ出力(システムエラー)
            LOGGER.error("Selecting CCPRINTERSETTING is failed. Some runtime exception happen on DB.", e);
            throw e;
            
        }finally{
            // データベース切断
            try{
                rs.close();
            }catch(Exception e){
            }
            try{
                dao.close();
            }catch(Exception e){
            }
        }

        // --------------------------------------------------
        // 終了処理の実行
        // --------------------------------------------------
        this.finalizeService();

        return ret;
    }

    /**
     * <p>起動条件 : ツリーの末端ノード押下時</p>
     * <p>処理概要 : 検索処理を実行する</p>
     * 
     * @param condition (I)条件データ
     * @param results (O)結果データ
     * @return 検索処理の成否
     * @throws LogicalException 論理的例外
     */
    public boolean executeSearch(Condition condition, List<Result> results) throws LogicalException{

        boolean ret = false;

        // --------------------------------------------------
        // 開始処理の実行
        // --------------------------------------------------
        try{
            if(!this.initializeService()){
                return false;
            }
        }catch(LogicalException e){
            // エラーログ出力(システムエラー)
            LOGGER.error("Service initialization is failed. Some runtime exception happen. Please check stacktrace.", e);
            throw e;
        }

        // --------------------------------------------------
        // 検索の実行
        // --------------------------------------------------
        APTemplateGeneralJDBCUtilDao dao = new APTemplateGeneralJDBCUtilDao();
        SimpleResultSet rs = null;
        try{
            // データベース接続
            dao.open();

            // SQLの設定
            dao.bindSQL(SQL_SELECT_CCPRINTERSETTING);

            dao.clearParameters();
            dao.setParameter("USERID", condition.userId);
            dao.setParameter("IPADDRESS", condition.ipAddress);

            // SQLの実行
            rs = dao.executeQuery();
            while(rs.next()){
                Result result = new Result();
                result.userId = rs.getString("USERID");
                result.ipAddress = rs.getString("IPADDRESS");
                result.cpPrinterName = rs.getString("CP_PRINTERNM");
                result.dpPrinterName = rs.getString("DP_PRINTERNM");
                result.lpPrinterName = rs.getString("LP_PRINTERNM");
                results.add(result);
            }
            // 該当データなしの場合
            int count = results.size();
            if(count <= 0){
                // メッセージ表示(該当データなし)
                this.setMessage("CMW00001");
                // 警告ログ出力(該当データなし)
                LOGGER.warn("Division data and user data is not found.");
                
            // 正常の場合
            }else{
                // メッセージ表示(検索正常終了)
                this.setMessage("CMN00004", NumberUtility.toDisplayFormat(count));
                // ログ出力(検索正常終了)
                LOGGER.info("Selecting division and user is success.");
                ret = true;
            }

        }catch(APTemplateSQLException e){
            // エラーログ出力(システムエラー)
            LOGGER.error("Selecting division and user is failed. Some runtime exception happen on DB.", e);
            throw e;
            
        }finally{
            // データベース切断
            try{
                rs.close();
            }catch(Exception e){
            }
            try{
                dao.close();
            }catch(Exception e){
            }
        }

        // --------------------------------------------------
        // 終了処理の実行
        // --------------------------------------------------
        this.finalizeService();

        return ret;
    }
}
