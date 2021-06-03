var main = {
    init : function () {
        var _this = this;
        $('#btn-post-save').on('click', function () {
            _this.save();
        });

    },
    save : function () {
        var data = {
            content: $('#content').val(),
            createdBy : $('#createdBy').val(),
            dDay : $('#dDay').val(),
            district : $('#district').val(),
            title : $('#title').val(),
            userId : $('#userId').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v2/post',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/post/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();