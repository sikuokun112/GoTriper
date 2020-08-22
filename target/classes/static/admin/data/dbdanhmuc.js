(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.danhmucid || client.danhmucid.indexOf(filter.danhmucid) > -1)
                    && (filter.tendanhmuc === undefined || client.tendanhmuc === filter.tendanhmuc) && (filter.sobaidang === undefined || client.sobaidang === filter.sobaidang);
            });
        },

        insertItem: function(insertingClient) {
            this.clients.push(insertingClient);
        },

        updateItem: function(updatingClient) { },

        deleteItem: function(deletingClient) {
            var clientIndex = $.inArray(deletingClient, this.clients);
            this.clients.splice(clientIndex, 1);
        }

    };
    window.db = db;

}());