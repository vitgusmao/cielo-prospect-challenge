import { Divider } from 'antd';
import Button from 'antd/es/button';
import Space from 'antd/es/space';
import Typography from 'antd/es/typography';
import styled from 'styled-components';

const StyledDiv = styled.div`
  .big-button {
    height: 180px;
    width: 200px;
  }
`;

function QuickActions() {
  return (
    <StyledDiv>
      <Typography.Title>Ações rápidas</Typography.Title>
      <Divider />
      <Space size="large">
        <Button className="big-button">Novo pré registro</Button>
        <Button className="big-button">Listar clientes</Button>
      </Space>
    </StyledDiv>
  );
}

export default QuickActions;
