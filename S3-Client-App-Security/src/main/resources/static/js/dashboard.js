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