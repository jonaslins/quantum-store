mongo localhost:27017/quantum-product <<-EOF
    rs.initiate({
        _id: "rs0",
        members: [ { _id: 0, host: "mongodb:27017" } ]
    });
EOF
echo "Initiated replica set"

sleep 3

mongo localhost:27017/admin <<-EOF
    db.createUser({ user: 'admin', pwd: 'admin', roles: [ { role: "userAdminAnyDatabase", db: "admin" } ], passwordDigestor: "server" });
EOF
echo "Created users"

mongo -u admin -p admin --authenticationDatabase admin localhost:27017/quantum-product <<-EOF
    use quantum-product;
    db.product.insert([
        { name : 'scooter', description: 'Small 2-wheel scooter', price : 3.14 },
        { name : 'car battery', description: '12V car battery', price : 8.1 },
        { name : '12-pack drill bits', description: '12-pack of drill bits with sizes ranging from #40 to #3', price : 0.8 },
        { name : 'hammer', description: "12oz carpenter's hammer", price : 0.75 },
        { name : 'hammer', description: "14oz carpenter's hammer", price : 0.875) },
        { name : 'hammer', description: "16oz carpenter's hammer", price : 1.0 },
        { name : 'rocks', description: 'box of assorted rocks', price : 5.3 },
        { name : 'jacket', description: 'water resistent black wind breaker', price : 0.1 },
        { name : 'spare tire', description: '24 inch spare tire', price : 22.2 }
    ]);
EOF

echo "Inserted example data"