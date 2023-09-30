import { FaPlus, FaPlusCircle, FaSearch } from 'react-icons/fa';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { ClientType } from '../../../../@types';
import colors from '../../../../colors';
import ContentHolder from '../../../../components/ContentHolder';
import Icon from '../../../../components/Icon';
import Scrollable from '../../../../components/Scrollable';
import { getApiEndpoint } from '../../../../data';
import Button from '../../../../facades/Button';
import Empty from '../../../../facades/Empty';
import List from '../../../../facades/List';
import useHttpGet from '../../../../hooks/useHttpGet';

import ListItem from './ListItem';

const StyledDiv = styled.div`
  display: flex;
  column-gap: 8px;
  justify-content: space-between;
  flex-grow: 1;
`;

const StyledButton = styled(Button)`
  border-color: ${colors.secondaryTitleColor};
  color: ${colors.secondaryTitleColor};
  &:hover {
    border-color: ${colors.primaryColor};
    color: ${colors.primaryColor};
  }
`;

function ClientList() {
  const clients = useHttpGet<ClientType[]>(getApiEndpoint('/clients'));
  const navigate = useNavigate();

  return (
    <ContentHolder
      label="Lista de clientes"
      level={2}
      extra={
        <StyledDiv>
          <Button
            icon={<Icon component={FaPlus} />}
            type="primary"
            onClick={() => {
              navigate('/new-client');
            }}
          >
            Adicionar cliente
          </Button>
          <StyledButton
            icon={<Icon component={FaSearch} />}
            onClick={() => {
              navigate('/analyze-client');
            }}
          >
            Analisar prospect
          </StyledButton>
        </StyledDiv>
      }
    >
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
        <Scrollable>
          <List
            dataSource={clients}
            renderItem={(item) => (
              <List.Item>
                <ListItem client={item} />
              </List.Item>
            )}
            itemLayout="vertical"
          />
        </Scrollable>
      )}
    </ContentHolder>
  );
}

export default ClientList;
