export function policy() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "/oss/policy",
            method: "get",
        }).then(({data}) => {
            resolve(data);
        })
    });
}