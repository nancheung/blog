$(function() {
    var testEditormdView;
    $('#main').addClass('loaded');
    var img=$("#avatar")[0].src;
    testEditormdView = editormd.markdownToHTML("article-content", {
        htmlDecode      : "style,script,<iframe",  // you can filter tags decode
        emoji           : true,
        taskList        : true,
        tex             : true,  // 默认不解析
        flowChart       : true,  // 默认不解析
        sequenceDiagram : true,  // 默认不解析 // 默认不解析
    });

    /*分享初始化*/
    $("#socialShare").socialShare({
        content: $("#"),
        url:getRootPath()+$("#article-url").attr("href"),
        title:$("#article-title").text(),
        summary:'NanCheung个人博客分享,欢迎指教',
        pic: img
    });

    $('#loader-wrapper .load_title').remove();
});





function getRootPath() {
    //获取当前网址，如： http://localhost:8080/GameFngine/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： GameFngine/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/GameFngine
    var projectName = pathName.substring(0,
        pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}
