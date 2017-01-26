package jp.co.canonits.prognerex.aptemplate_desktopaplike.CC3030.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.canonits.prognerex.aptemplate_desktopaplike.service.BaseService;
import jp.co.canonits.prognerex.core.common.message.MessageManager;

public class CC3030S01 extends BaseService {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ログインスタンス
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CC3030S01.class);

    // --------------------------------------------------------------------------
    // 内部変数
    // --------------------------------------------------------------------------

    // ------------------------------------------------------
    // SQL
    // ------------------------------------------------------

    // --------------------------------------------------------------------------
    // 公開クラス
    // --------------------------------------------------------------------------

    // --------------------------------------------------
    // I/Oパラメータ
    // --------------------------------------------------

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

}
