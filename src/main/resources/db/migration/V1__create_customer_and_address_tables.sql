-- Cria a tabela de clientes (customers)
CREATE TABLE customers (
                           customer_id UUID PRIMARY KEY NOT NULL UNIQUE,
                           name VARCHAR(150) NOT NULL,
                           email VARCHAR(150) UNIQUE,
                           phone_number VARCHAR(30) NOT NULL,
                           birth_date DATE,
                           age INTEGER,
                           created_at TIMESTAMP WITH TIME ZONE,
                           updated_at TIMESTAMP WITH TIME ZONE,
                           deleted_at TIMESTAMP WITH TIME ZONE,
                           active BOOLEAN
);

-- Cria a tabela de endereços (addresses)
CREATE TABLE addresses (
                           address_id UUID PRIMARY KEY,
                           street VARCHAR(255) NOT NULL,
                           number VARCHAR(50) NOT NULL,
                           complement VARCHAR(255),
                           neighborhood VARCHAR(255) NOT NULL,
                           city VARCHAR(255) NOT NULL,
                           state VARCHAR(2) NOT NULL,
                           zip_code VARCHAR(20) NOT NULL,
    -- Chave estrangeira para conectar o endereço ao cliente
                           customer_id UUID NOT NULL,
    -- Define a restrição (constraint) da chave estrangeira
                           CONSTRAINT fk_customer FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);