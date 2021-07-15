var main = {
    init : function () {
        var _this = this;
        $('#btn-lesson-save').on('click', function () {
            _this.save();
        });

    },
    save : function () {
        var data = {
            name: $('#inputLessonName').val(),
            e : $('#inputLessonE').val(),
            n : $('#inputLessonN').val(),
            address : $('#inputLessonAddress').val(),
            web : $('#inputLessonWeb').val(),
            info : $('#inputLessonInfo').val(),
            district : $('#inputLessonDistrict').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v2/lesson',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('코트가 등록되었습니다.');
            window.location.href = '/admin/lesson';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();