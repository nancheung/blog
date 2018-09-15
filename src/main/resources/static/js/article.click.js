 var pager;

/*将初始化页面封装成一个方法*/
function initPage(id) {

    $("#total-num").text(pager.totalCount);
    $("#total-page").text(pager.totalPageNum);
    $("#current-page").text(pager.page);
    $.jqPaginator('#pagination', {
        totalPages: pager.totalPageNum,
        totalCounts: pager.totalCount,
        visiblePages: 5,
        currentPage: pager.page,
        prev: '<li class="prev"><a href="javascript:;">Previous</a></li>',
        next: '<li class="next"><a href="javascript:;">Next</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            pager.page = num;
            var type = $("#pagination").data("type");
            loadList(type, id);
            // 当前第几页
            $("#current-page").text(num);
            $(".chosen-select").chosen({
                max_selected_options: 5,
                no_results_text: "没有找到",
                allow_single_deselect: true
            });
            $(".chosen-select").trigger("liszt:updated");
        }
    });
}

/*将加载文章,文章分类,标签分类重构成一个方法*/
function loadList(type, id) {
    var url = "";
    var img=$("#avatar")[0].src;
    var domain = 'http://'+document.domain;
    if (type === "article") {
        url = '/' + type + '/load';
    }
    else {
        url = '/' + type + '/load/' + id;
    }
    $.ajax({
        type: 'GET',
        url: url,
        data: pager,
        success: function (data) {
            $("#main-article").html(data);
            //初始化文章分页信息
            //初始化文章
            /*分享初始化*/
            $(".socialShare").socialShare({
                content: "NanCheung在IT,生活,音乐方面的分享",
                url: domain,
                title: $("#article-title").text(),
                summary: 'NanCheung个人博客分享,欢迎指教',
                pic: img
            });
            $('#loader-wrapper .load_title').remove();
        }
    });
    //2018年5月26日19点36分 By NanCheung 添加翻页后返回页面顶部
    $('body, html').animate({scrollTop: 0}, 600);
}

$("#main-article").on('click', '.article-tag-link', function () {
    var tagId = $(this).data("id");
    window.location.href = '/tags/details/' + tagId;
})

/*文章归档点击事件*/
$(".archive-list-link").on('click', function () {
    var createTime = $(this).data("id");
    window.location.href = '/archive/details/' + createTime;
})
/*文章分类点击事件*/
$(".category-list-link").on('click', function () {
    var categoryId = $(this).data("id");
    window.location.href = '/categories/details/' + categoryId;
})

/*为动态元素绑定lick事件*/
$("#main-article").on('click', '.article-category-link', function () {
    var categoryId = $(this).data("id");
    window.location.href = '/categories/details/' + categoryId;
})
