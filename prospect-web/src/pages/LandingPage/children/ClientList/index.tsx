import { FaPlusCircle } from 'react-icons/fa';
import { Link } from 'react-router-dom';

import { Client } from '../../../../@types';
import ContentHolder from '../../../../components/ContentHolder';
import Icon from '../../../../components/Icon';
import { getApiEndpoint } from '../../../../data';
import Button from '../../../../facades/Button';
import Empty from '../../../../facades/Empty';
import List from '../../../../facades/List';
import Typography from '../../../../facades/Typography';
import useHttpGet from '../../../../hooks/useHttpGet';

function ClientList() {
  const clients = useHttpGet<Client[]>(getApiEndpoint('/clients'));

  console.log(clients);

  return (
    <ContentHolder label="Lista de clientes" level={2}>
      {clients?.length === 0 ? (
        <Empty description="a lista de clientes está vazia">
          <Button
            type="primary"
            icon={<Icon component={FaPlusCircle} />}
            onClick={(e) => {
              e.preventDefault();
            }}
          >
            <Link to="../new-client">Registrar novo cliente</Link>
          </Button>
        </Empty>
      ) : (
        <List
          dataSource={clients}
          renderItem={(item) => (
            <List.Item>
              {/* <List.Item.Meta
                title={<Typography.Text strong>{item.title}</Typography.Text>}
                description=""
              /> */}
            </List.Item>
          )}
        />
      )}
    </ContentHolder>
  );
}

export default ClientList;
