
/*初始化文章分页信息*/
$(function () {
    $('body').addClass('loaded');
            $.ajax({
                type: 'GET',
                url: '/pager/articles/load',
                data:pager,
                success: function (data){
                    pager = data;
                    $("#pagination").data("type","article");
                    //由于插件在没有数据的时候会报错，所以添加一层判断
                    if (pager.totalCount > 0){
                        initPage(null);
                    }
                }
        });

})




