$('#table-region').DataTable({
    ajax: {
        url: '/api/region',
        dataSrc: ''
    },
    columns: [{
        data: 'id'
    }, {
        data: 'name'
    }, {
        data: null,
        render: function (data, type, row, meta) {
            return ` <button type="button" style = "background-color: #3D426B; color: #F8F7F3;" class="btn" data-bs-toggle="modal"           data-bs-target="#detailRegion" onclick="findById(${data.id})">
                Detail
            </button>
            <button type="button" style = "background-color: #3D426B; color: #F8F7F3;" class="btn" data-bs-toggle="modal"           data-bs-target="#updateRegion" onclick="beforeUpdate(${data.id})">
                Edit
            </button>
            <button style = "color: red;" class="btn" onclick="deleteRegion(${data.id})">Delete</button>
            `;
        }
    }]
})
function findById(id) {
    $.ajax({
        method: "GET",
        url: "/api/region/" + id,
        dataType: "JSON",
        success: result => {
            $("#region-id").text(`${result.id}`)
            $("#region-name").text(`${result.name}`)
        }
    })
}
function create() {
    let nameVal = $('#insert-region-name').val()
    $.ajax({
        method: "POST",
        url: "/api/region",
        dataType: "JSON",
        beforeSend:addCsrfToken(),
        data: JSON.stringify({
            name: nameVal
        }),
        contentType: "application/json",
        success: result => {
            $('#createRegion').modal('hide')
            $('#table-region').DataTable().ajax.reload()
            $('#insert-region-name').val("")
            Swal.fire({
                position: 'center',
                imageUrl: 'https://www.history.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTU3ODc4NjAzNzkzMzExNDU1/hith-the-birth-of-ok-175-years-ago-istock_000016716612large-2.jpg',
                imageWidth: 200,
                imageHeight: 200,
                imageAlt: 'Success',
                title: 'Region has been created!',
                showConfirmButton: false,
                timer: 1500,
                background:
                `rgba(248, 247, 243, 1)`
            })
        }
    })
}

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "/api/region/" + id,
        dataType: "JSON",
        success: result => {
            // $("#region-id").text(`${result.id}`)
            $("#update-region-name").val(`${result.name}`)
            $("#update-region-id").val(`${result.id}`)
        }
    })
}


function update() {
    let nameVal = $("#update-region-name").val()
    let idVal = $("#update-region-id").val()

    Swal.fire({
        title: 'Are you sure?',
        text: "You WILL change the region detail!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sure',
        background:
                `rgba(248, 247, 243, 1)`
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "/api/region/" + idVal,
                dataType: "JSON",
                beforeSend:addCsrfToken(),
                data: JSON.stringify({
                    name: nameVal
                }),
                contentType: "application/json",
                success: result => {
                    $('#updateRegion').modal('hide')
                    $('#table-region').DataTable().ajax.reload()
                    $('#update-region-name').val("")
                    Swal.fire({
                        position: 'center',
                        imageUrl: 'https://www.history.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTU3ODc4NjAzNzkzMzExNDU1/hith-the-birth-of-ok-175-years-ago-istock_000016716612large-2.jpg',
                        imageWidth: 200,
                        imageHeight: 200,
                        imageAlt: 'Success',
                        title: 'Region has been updated!',
                        showConfirmButton: false,
                        timer: 1500,
                        background:
                        `rgba(248, 247, 243, 1)`
                    })
                }
            })
        }
    })
}

function deleteRegion(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You WILL delete this entry!",
        imageUrl: 'https://www.reactiongifs.us/wp-content/uploads/2018/06/giphy-14.gif',
        imageWidth: 250,
        imageHeight: 200,
        imageAlt: 'Warnung',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, update it!',
        background:
                `rgba(248, 247, 243, 1)`
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "/api/region/" + id,
                beforeSend:addCsrfToken(),
                dataType: "JSON",
                success: result => {
                    $('#table-region').DataTable().ajax.reload() 
                    Swal.fire({
                        icon: 'success',
                        title: 'Region has been deleted!',
                        width: 600,
                        padding: '3em',
                        color: '#716add',
                        background: '#fff',
                        showConfirmButton: false,
                        timer: 1500,
                        background:
                        `rgba(248, 247, 243, 1)`
                    })
                }
            })
        }
    })
}

$('#logout').on('click', function(){
    Swal.fire({
        title: 'Are you sure?',
        text: "You WILL out of this page",
        imageUrl: 'https://www.reactiongifs.us/wp-content/uploads/2018/06/giphy-14.gif',
        imageWidth: 250,
        imageHeight: 200,
        imageAlt: 'Warnung',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sure',
        background:
                `rgba(248, 247, 243, 1)`
    }).then((result)=>{
        if(result.isConfirmed){
            document.logoutForm.submit()
        }
    })
});
    
