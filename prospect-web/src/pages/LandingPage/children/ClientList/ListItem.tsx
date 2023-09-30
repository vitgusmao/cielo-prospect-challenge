import { FaEdit } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { ClientType } from '../../../../@types';
import Icon from '../../../../components/Icon';
import Button from '../../../../facades/Button';
import Descriptions from '../../../../facades/Descriptions';
import List from '../../../../facades/List';
import Tag from '../../../../facades/Tag';
import Typography from '../../../../facades/Typography';
import { isLegalEntity } from '../../../../utils';

const StyledDescription = styled.div`
  display: flex;
`;

const StyledMeta = styled(List.Item.Meta)`
  margin-block-end: 0 !important;
`;

function ListItem({ client }: { client: ClientType }) {
  const navigate = useNavigate();
  if (isLegalEntity(client)) {
    return (
      <StyledMeta
        title={
          <Descriptions size="small">
            <Descriptions.Item>
              Pessoa jurídica{' '}
              <Button
                icon={<Icon component={FaEdit} />}
                type="link"
                onClick={() => {
                  navigate(`/edit-client/${client.id}`);
                }}
              />
            </Descriptions.Item>
          </Descriptions>
        }
        description={
          <StyledDescription>
            <div>
              <Descriptions size="small">
                <Descriptions.Item
                  label={<Typography.Text strong>Razão social</Typography.Text>}
                >
                  {client.corporateReason}
                </Descriptions.Item>
              </Descriptions>
              <Descriptions size="small">
                <Descriptions.Item label="CNPJ">
                  {client.cnpj}
                </Descriptions.Item>
                <Descriptions.Item label="MCC">{client.mcc}</Descriptions.Item>
              </Descriptions>
            </div>
            <div>
              <Tag color="error">{client.status}</Tag>
            </div>
          </StyledDescription>
        }
      />
    );
  }
  return (
    <StyledMeta
      title={
        <Descriptions size="small">
          <Descriptions.Item>
            Pessoa física{' '}
            <Button
              icon={<Icon component={FaEdit} />}
              type="link"
              onClick={() => {
                navigate(`/edit-client/${client.id}`);
              }}
            />
          </Descriptions.Item>
        </Descriptions>
      }
      description={
        <StyledDescription>
          <div>
            <Descriptions size="small">
              <Descriptions.Item
                label={<Typography.Text strong>Nome</Typography.Text>}
              >
                {client.name}
              </Descriptions.Item>
            </Descriptions>
            <Descriptions size="small">
              <Descriptions.Item label="CPF">{client.cpf}</Descriptions.Item>
              <Descriptions.Item label="MCC">{client.mcc}</Descriptions.Item>
            </Descriptions>
          </div>
          <div>
            <Tag color="error">{client.status}</Tag>
          </div>
        </StyledDescription>
      }
    />
  );
}

export default ListItem;
