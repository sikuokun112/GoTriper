(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.tinhthanhid || client.tinhthanhid.indexOf(filter.tinhthanhid) > -1)
                    && (filter.tinhthanhname === undefined || client.tinhthanhname === filter.tinhthanhname)&& (filter.sodiadiem === undefined || client.sodiadiem === filter.sodiadiem);
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