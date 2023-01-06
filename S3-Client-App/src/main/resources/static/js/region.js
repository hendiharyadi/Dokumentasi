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
            return ` <button type="button" class="btn btn-primary" data-bs-toggle="modal"           data-bs-target="#detailRegion" onclick="findById(${data.id})">
                Detail
            </button>
            <button type="button" class="btn btn-warning" data-bs-toggle="modal"           data-bs-target="#updateRegion" onclick="beforeUpdate(${data.id})">
                Edit
            </button>
            <button class="btn btn-danger" onclick="deleteRegion(${data.id})">Delete</button>
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
                icon: 'success',
                title: 'Region has been created!',
                showConfirmButton: false,
                timer: 1500
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
        text: "You won't change region name!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, update it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "/api/region/" + idVal,
                dataType: "JSON",
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
                        icon: 'success',
                        title: 'Region has been updated!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            })
        }
    })
}

function deleteRegion(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't delete this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "/api/region/" + id,
                dataType: "JSON",
                success: result => {
                    $('#table-region').DataTable().ajax.reload() 
                    Swal.fire({
                        icon: 'success',
                        title: 'Region has been Deteled!',
                        width: 600,
                        padding: '3em',
                        color: '#716add',
                        background: '#fff',
                        showConfirmButton: false,
                        timer: 1500,
                        backdrop: `
                        rgba(0,0,123,0.4)
                        url("https://sweetalert2.github.io/images/nyan-cat.gif")
                        left top
                        no-repeat
                        `
                    })
                }
            })
        }
    })
}